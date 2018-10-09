package com.webpos.controller;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.webpos.entity.Message;
import com.webpos.service.MessageService;
import com.webpos.tools.Md5Encrypt;

@ServerEndpoint(value = "/websocket", configurator = SpringConfigurator.class)
public class MyWebsocket {

	private static CopyOnWriteArraySet<MyWebsocket> websocketPools = new CopyOnWriteArraySet<MyWebsocket>();

	private Session session;
	//type;1、聊天信息 2、登录信息  3、下注   4、开奖

	@Autowired
	private MessageService messageService;
	// boolean index = false;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) throws Exception {
		this.session = session;
		websocketPools.add(this);
		
		send("2&&__"+websocketPools.size(),session,true);
		// index = true;
		// onMessage("&&open&&", this.session);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		websocketPools.remove(this);
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws Exception {

		String ms[] = message.split("&&__");

		if (ms.length != 5) {
			return;
		}
		String userid = ms[1];
		String roomid = ms[0];
		String msg = ms[3];
		String type = ms[2];
		String sign = ms[4];

		String self_sign = Md5Encrypt.md5(roomid + "@" + userid + "!#@#Qsaswe@#./1!" + "@" + msg);
		if (!sign.equals(self_sign)) {
			System.out.println("签名错误");
			return;
		}
		
		if(type.equals("1")) {//聊天信息
			send("1&&__"+msg,session,false);
			
			try {
				Message m = new Message();
				m.setCtime(new Date());
				m.setMessage(msg);
				if (roomid != null) {
					m.setRoomid(Integer.parseInt(roomid));
				}
				m.setUserid(userid);
				messageService.insert(m);
			} catch (Exception e) {
			}
		}else if(type.equals("3")) {//有人下注
			send("3&&__"+msg,session,false);
		}
		else if(type.equals("4")) {//控制开奖
			send("4&&__"+msg,session,false);
		}
		
	}

	private void send(String message,Session mysession,boolean ifconcludemy)  {
		
		for (MyWebsocket item : websocketPools) {
			if(!ifconcludemy) {
				if(item.session==mysession)
				{
					continue;
				}
			}
			try {
				item.session.getAsyncRemote().sendText(message);
				//item.send(msg);
			} catch (Exception e) {
			}
		}
		
		//this.session.getAsyncRemote().sendText(message);
		// this.session.getBasicRemote().sendText(message);

	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		// System.out.println("发生错误");
		// error.printStackTrace();
	}
}
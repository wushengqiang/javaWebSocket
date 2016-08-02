package com.jacken.myProject.core;

import com.jacken.myProject.util.SessionUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/31.
 */
@ServerEndpoint("/websocket.ws/{relationId}/{userCode}")
public class SeverSocketEndPoint {

    @OnOpen
    public void onOpen(@PathParam("relationId") String relationId, @PathParam("userCode") int userCode, Session session){

        System.out.println("scoket is connecting");
        SessionUtils.put(relationId,userCode,session);
    }

    @OnMessage
    public void onMessage(@PathParam("relationId") String relationId,@PathParam("userCode") int userCode,String message) throws  Exception{

        System.out.println("Revice a message:"+message);
        int count=0;
        while(count<10){

//            Set<String> keys=SessionUtils.clients.keySet();
//            Iterator<String> it=keys.iterator();
//
//            while(it.hasNext()){
//
//                 SessionUtils.clients.get(it.next()).getBasicRemote().sendText("I hava reviced your word:"+message);
//            }

            SessionUtils.clients.get(SessionUtils.getKey(relationId,userCode)).getBasicRemote().sendText("I hava recevied as message:"+message);

            Thread.sleep(3000);
            count++;
        }

    }

    @OnError
    public void onError(@PathParam("relationId") String relationId,@PathParam("userCode") int userCode,Throwable throwable,Session session){

        System.out.println("Scokect has throwable:"+throwable.getMessage());
        SessionUtils.remove(relationId,userCode);
    }

    @OnClose
    public void onClose(@PathParam("relationId") String relationId,@PathParam("userCode") int userCode,Session session){

        System.out.println(relationId+"_"+userCode+" session is closing");
        SessionUtils.remove(relationId,userCode);
    }
}

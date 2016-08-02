package com.jacken.myProject.util;

import com.google.common.collect.Maps;

import javax.websocket.Session;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/31.
 */
public class SessionUtils {

    public static Map<String,Session> clients= Maps.newConcurrentMap();

    public static  void put(String relationId, int userCode, Session session){

            clients.put(getKey(relationId,userCode),session);
    }

    public static Session get(String relationId,int userCode){

        return clients.get(getKey(relationId,userCode));
    }

    public static boolean hasConnection(String relationId,int userCode){

        return  clients.containsKey(getKey(relationId,userCode));
    }

    public static void remove(String relationId,int userCode){

        if(hasConnection(relationId,userCode)){

            clients.remove(getKey(relationId,userCode));
        }
    }

    public static  String getKey(String relationId,int userCode){

        return relationId+"_"+userCode;
    }
}

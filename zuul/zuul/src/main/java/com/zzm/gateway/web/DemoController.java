package com.zzm.gateway.web;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rx.Observable;
import rx.Subscriber;

import com.zzm.gateway.event.RefreshRouteService;

/**
 * Created by zzm on 2017/4/1.
 * 由于DemoController访问是/路径，如果路由有配置/**就会冲突
 */
@RestController
public class DemoController {

    @Autowired
    RefreshRouteService refreshRouteService;
    
     public DemoController() {
    	 Observable.interval(10, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {  
      	      @Override public void onCompleted() {  
      	    	  System.out.println("onCompleted");
      	      }  
      	      @Override public void onError(Throwable e) {  
      	  
      	      }  
      	      @Override public void onNext(Long aLong) {  
      	    	System.out.println("refreshRouteService.refreshRoute>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
      	    	 refreshRouteService.refreshRoute();
      	      }  
      	    });  
	}

    @RequestMapping("/refreshRoute")
    public String refreshRoute(){
        refreshRouteService.refreshRoute();
        return "refreshRoute";
    }

    @Autowired
    ZuulHandlerMapping zuulHandlerMapping;

    @RequestMapping("/watchNowRoute")
    public String watchNowRoute(){
        //可以用debug模式看里面具体是什么
        Map<String, Object> handlerMap = zuulHandlerMapping.getHandlerMap();
        System.out.println(handlerMap);
        return "watchNowRoute";
    }



}

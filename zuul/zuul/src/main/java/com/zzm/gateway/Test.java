package com.zzm.gateway;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 Observable.interval(10, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {  
   	      @Override public void onCompleted() {  
   	    	  System.out.println("onCompleted");
   	      }  
   	      @Override public void onError(Throwable e) {  
   	  
   	      }  
   	      @Override public void onNext(Long aLong) {  
   	    	System.out.println("onNext");
   	      }  
   	    });  
		 System.in.read();
		 
	}

}

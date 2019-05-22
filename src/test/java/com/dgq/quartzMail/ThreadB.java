package com.dgq.quartzMail;

public class ThreadB extends Thread{
	
	private MyList list;
    public ThreadB(MyList list){
        this.list=list;

    }

    @Override
    public void run() {
        try {
        	System.out.println(list.size());
            while (true){
                if (list.size() == 5) {
                	System.out.println("==5,线程b要退出了");
	                throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

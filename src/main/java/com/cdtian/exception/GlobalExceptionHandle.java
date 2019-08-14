package com.cdtian.exception;

public class GlobalExceptionHandle {
    public static void main(String[] args) {
//        try {
            GlobalException handle = new GlobalException();
            Thread.setDefaultUncaughtExceptionHandler(handle);
            int a = 3 / 0;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

}


class GlobalException implements Thread.UncaughtExceptionHandler {

    public GlobalException() {
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("catch exception.........");
        if(e instanceof ArithmeticException){
            System.out.println( e.getMessage());
        }
    }
}

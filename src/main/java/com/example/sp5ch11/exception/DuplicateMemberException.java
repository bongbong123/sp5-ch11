package com.example.sp5ch11.exception;

//동일한 이메일을 갖고 있는 회원이 이미 존재할 경우 발생
public class DuplicateMemberException extends RuntimeException{
    public DuplicateMemberException(String message) { super(message);}
}

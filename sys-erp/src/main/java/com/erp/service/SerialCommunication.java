//package com.erp.service;
//import com.fazecast.jSerialComm.SerialPort;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SerialCommunication implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) {
//        SerialPort serialPort = SerialPort.getCommPorts()[0]; // 첫 번째 COM 포트 선택
//        serialPort.setComPortParameters(9600, 8, 1, 0); // 포트 설정
//        serialPort.openPort(); // 포트 열기
//        
//        System.out.println("시리얼 포트 열림. 카드 ID를 기다리는 중...");
//
//        // 카드 ID를 읽어오기 위한 루프
//        while (true) {
//            if (serialPort.bytesAvailable() > 0) {
//                byte[] readBuffer = new byte[serialPort.bytesAvailable()];
//                serialPort.readBytes(readBuffer, readBuffer.length);
//                String cardID = new String(readBuffer);
//                System.out.println("받은 카드 ID: " + cardID.trim()); // 카드 ID 출력
//            }
//        }
//    }
//}

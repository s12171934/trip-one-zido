package com.example.triponezidoapi.util;

public class LocationCode {
    public static int getCode(String address){
        String addressSiDo = address.substring(0,2);

        switch (addressSiDo){
            case "서울" :
                return 11;
            case "부산" :
                return 21;
            case "대구" :
                return 22;
            case "인천" :
                return 23;
            case "광주" :
                return 24;
            case "대전" :
                return 25;
            case "울산" :
                return 26;
            case "세종" :
                return 29;
            case "경기" :
                return 31;
            case "강원" :
                return 32;
            case "충북" :
                return 33;
            case "충남" :
                return 34;
            case "전북" :
                return 35;
            case "전남" :
                return 36;
            case "경북" :
                return 37;
            case "경남" :
                return 38;
            case "제주" :
                return 39;
            case "충청" :
                if(address.charAt(2) == '북'){
                    return 33;
                } else{
                    return 34;
                }
            case "전라" :
                if(address.charAt(2) == '북'){
                    return 35;
                } else{
                    return 36;
                }
            case "경상" :
                if(address.charAt(2) == '북'){
                    return 37;
                } else{
                    return 38;
                }
            default:
                return 0;
        }
    }
}

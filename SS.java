package Sunshine;

public class SS{

public static void main(String[] args){

int i=(int)(Math.random()*50+50);

int j;

System.out.println("����������СΪ��"+i);  

for(j=2;j<=i;j++) 

{if(i%j==0) 

break; }

if(j==i)

System.out.println(i+"������"); 

}

} 
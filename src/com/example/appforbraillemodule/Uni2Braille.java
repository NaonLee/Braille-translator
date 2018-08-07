package com.example.appforbraillemodule;

import java.util.LinkedList;

//�����ڵ��ε��� - > ���ڹ���Ʈ�� ������
public class Uni2Braille {

 // �ʼ� �ε��� ->
 public static LinkedList<Integer> unicho2cho(int n) {
  LinkedList<Integer> choINT = new LinkedList<Integer>();

  switch (n) {
  case 0:
   choINT.add((Integer) 65);
   break;
  case 1:
   choINT.add((Integer) 65);
   choINT.add((Integer) 78);
   break;
  case 2:

   choINT.add((Integer) 66);
   break;
  case 3:

   choINT.add((Integer) 67);
   break;
  case 4:
   choINT.add((Integer) 65);
   choINT.add((Integer) 78);
   break;
  case 5:
   choINT.add((Integer) 68);
   break;
  case 6:
   choINT.add((Integer) 69);
   break;
  case 7:
   choINT.add((Integer) 70);
   break;
  case 8:
   choINT.add((Integer) 70);
   choINT.add((Integer)78);
   break;
  case 9:
   choINT.add((Integer) 71);
   break;
  case 10:

   choINT.add((Integer) 71);
   choINT.add((Integer) 78);
   break;
  case 11:
   break; // ����
  case 12:

   choINT.add((Integer) 72);
   break;
  case 13:

   choINT.add((Integer) 72 );
   choINT.add((Integer)  78);
   break;
  case 14:

   choINT.add((Integer) 73);
   break;
  case 15:

   choINT.add((Integer) 74);
   break;
  case 16:

   choINT.add((Integer) 75);
   break;
  case 17:

   choINT.add((Integer) 76);
   break;
  case 18:

   choINT.add((Integer) 77);
   break;
  }

  return choINT;
 }

 public static LinkedList<Integer> unijung2mo(int n) {
  LinkedList<Integer> moINT = new LinkedList<Integer>(); // �����̴� ����
                // �����äŤǤˤǤ�
  switch (n) {
  case 0:
   moINT.add((Integer) 79);
   break;
  case 1:
   moINT.add((Integer) 89  );
   break;
  case 2:
   moINT.add((Integer) 80 );
   break;
  case 3:
   moINT.add((Integer) 80);
   moINT.add((Integer) 96);
   break;
  case 4:
   moINT.add((Integer) 81);
   break;
  case 5:
   moINT.add((Integer) 90);
   break;
  case 6:
   moINT.add((Integer) 82);
   break;
  case 7:
   moINT.add((Integer) 95);
   break;
  case 8:
   moINT.add((Integer) 83);
   break;
  case 9:
   moINT.add((Integer) 92);
   break;
  case 10:
   moINT.add((Integer) 83);
   moINT.add((Integer) 96);
   break;
  case 11:
   moINT.add((Integer) 91 );
   break;
  case 12:
   moINT.add((Integer) 84);
   break;
  case 13:
   moINT.add((Integer) 85);
   break;
  case 14:
   moINT.add((Integer) 93);
   break;
  case 15:
   moINT.add((Integer) 93);
   moINT.add((Integer) 96);
   break;
  case 16:
   moINT.add((Integer) 85);
   moINT.add((Integer) 96 );
   break;
  case 17:
   moINT.add((Integer) 86);
   break;
  case 18:
   moINT.add((Integer) 87 );
   break;
  case 19:
   moINT.add((Integer) 94 );
   break;
  case 20:
   moINT.add((Integer) 88);
   break;
  }

 
  return moINT;
 }

 public static LinkedList<Integer> unijong2jong(int n) {
  LinkedList<Integer> jongINT = new LinkedList<Integer>(); // ����
  switch (n) {
  case 0:
   break;// /���������� ' '
  case 1:
   // binx = 80 ;
   jongINT.add((Integer) 33);
   break;
  case 2:
   // binx = 100;//100 +0 ��
   jongINT.add((Integer) 33);
   jongINT.add((Integer) 33);
   break;
  case 3:
   // binx = 106;//100+6
   jongINT.add((Integer) 33);
   jongINT.add((Integer) 39);
   break;
  case 4:
   // binx = 81 ;
   jongINT.add((Integer) 34);
   break;
  case 5:
   // binx = 108;//100+8
   jongINT.add((Integer) 34);
   jongINT.add((Integer) 41);
   break;
  case 6:
   // binx = 113;//100+13
   jongINT.add((Integer) 34);
   jongINT.add((Integer) 46);
   break;
  case 7:
   jongINT.add((Integer) 35);
   break;
  case 8:
   jongINT.add((Integer) 36);
   break;
  case 9:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 33);
   break;
  case 10:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 37);
   break;
  case 11:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 38);
   break;
  case 12:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 39);
   break;
  case 13:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 44);
   break;
  case 14:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 45);
   break;
  case 15:
   jongINT.add((Integer) 36);
   jongINT.add((Integer) 46);//��
  
   break;
  case 16:
   jongINT.add((Integer) 37);//����
   break;
  case 17:
   jongINT.add((Integer) 38);
   break;
  case 18:
   jongINT.add((Integer) 38);
   jongINT.add((Integer) 39);
   break;
  case 19:
   jongINT.add((Integer) 39);
   break;
  case 20:
   jongINT.add((Integer) 39);
   jongINT.add((Integer) 39);
   break;
  case 21:
   jongINT.add((Integer) 40);
   break;
  case 22:
   jongINT.add((Integer) 41);
   break;
  case 23:
   jongINT.add((Integer) 42);
   break;
  case 24:
   jongINT.add((Integer) 43);
   break;
  case 25:
   jongINT.add((Integer) 44);
   break;
  case 26:
   jongINT.add((Integer) 45);
   break;
  case 27:
   jongINT.add((Integer) 46);
   break;
  }

  return jongINT;
 }
//���� -> �ҹ����϶� �����ڵ�/ �빮���϶� �빮�� ǥ�� + �ҹ��������ڵ�
 public static LinkedList<Integer> Eng(int n) {
  LinkedList<Integer> engINT = new LinkedList<Integer>(); // ����
  if (n >= 65 && n < 90)// �빮��
  {
   engINT.add(126);// �빮��ǥ�� '~'
   engINT.add(n + 32);// �ҹ��ڿ� ����ǥ���� ����

  } else if (n >= 97 && n < 122)
   engINT.add(n);// �ҹ���
  return engINT;
 }

}
#Q = {init,temp,mul,final_state}
#S = {0,1,2,3,4,5,6,7,8,9,*}
#G = {*,0,1,2,3,4,5,6,7,8,9,=,_}
#F = {final_state}
#q0 = init
#B = _
#N = 2
#D init 9_ _9 r* mul
#D init 8_ _8 r* mul
#D init 7_ _7 r* mul
#D init 6_ _6 r* mul
#D init 5_ _5 r* mul
#D init 4_ _4 r* mul
#D init 3_ _3 r* mul
#D init 2_ _2 r* mul
#D init 1_ _1 r* mul
#D init 0_ _0 r* mul
#D mul 00 0_ ** final_state
#D mul 22 4_ ** final_state
#D mul 01 0_ ** final_state
#D mul 23 6_ ** final_state
#D mul 02 0_ ** final_state
#D mul 24 8_ ** final_state
#D mul 03 0_ ** final_state
#D mul 04 0_ ** final_state
#D mul 05 0_ ** final_state
#D mul *0 =0 r* mul
#D mul 06 0_ ** final_state
#D mul *1 =1 r* mul
#D mul 07 0_ ** final_state
#D mul *2 =2 r* mul
#D mul 08 0_ ** final_state
#D mul *3 =3 r* mul
#D mul 09 0_ ** final_state
#D mul *4 =4 r* mul
#D mul *5 =5 r* mul
#D mul *6 =6 r* mul
#D mul *7 =7 r* mul
#D mul *8 =8 r* mul
#D mul *9 =9 r* mul
#D mul 50 0_ ** final_state
#D mul 51 5_ ** final_state
#D mul 30 0_ ** final_state
#D mul 31 3_ ** final_state
#D mul 10 0_ ** final_state
#D mul 32 6_ ** final_state
#D mul 11 1_ ** final_state
#D mul 33 9_ ** final_state
#D mul 12 2_ ** final_state
#D mul 13 3_ ** final_state
#D mul 14 4_ ** final_state
#D mul 15 5_ ** final_state
#D mul 16 6_ ** final_state
#D mul 17 7_ ** final_state
#D mul 18 8_ ** final_state
#D mul 19 9_ ** final_state
#D mul 40 0_ ** final_state
#D mul 41 4_ ** final_state
#D mul 20 0_ ** final_state
#D mul 42 8_ ** final_state
#D mul 21 2_ ** final_state
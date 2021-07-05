#Q = {add,init,get_num,final_state}
#S = {0,1,2,3,4,5,6,7,8,9,+}
#G = {+,0,1,2,3,4,5,6,7,8,9,=,_}
#F = {final_state}
#q0 = init
#B = _
#N = 2
#D add 11 2_ *l add
#D add 01 1_ *l add
#D add 9_ 9_ ** final_state
#D add 8_ 8_ ** final_state
#D add 7_ 7_ ** final_state
#D add 6_ 6_ ** final_state
#D add 5_ 5_ ** final_state
#D add 4_ 4_ ** final_state
#D add 4_ 3_ ** final_state
#D add 3_ 3_ ** final_state
#D add 2_ 2_ ** final_state
#D add 1_ 1_ ** final_state
#D add 0_ 0_ ** final_state
#D add 81 9_ *l add
#D add 71 8_ *l add
#D add 61 7_ *l add
#D add 51 6_ *l add
#D add 41 5_ *l add
#D add 41 2_ *l add
#D add 31 4_ *l add
#D add 21 3_ *l add
#D init 9_ 81 *r get_num
#D init 8_ 71 *r get_num
#D init 7_ 61 *r get_num
#D init 6_ 51 *r get_num
#D init 5_ 41 *r get_num
#D init 4_ 31 *r get_num
#D init 4_ 41 *r get_num
#D init 3_ 21 *r get_num
#D init 2_ 11 *r get_num
#D init 1_ 01 *r get_num
#D get_num +_ =_ rl add
#D get_num 9_ 81 *r get_num
#D get_num 8_ 71 *r get_num
#D get_num 7_ 61 *r get_num
#D get_num 6_ 71 *r get_num
#D get_num 5_ 61 *r get_num
#D get_num 4_ 51 *r get_num
#D get_num 3_ 21 *r get_num
#D get_num 2_ 11 *r get_num
#D get_num 1_ 01 *r get_num
#D get_num 0_ __ r* get_num
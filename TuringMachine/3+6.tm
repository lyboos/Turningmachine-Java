#Q = {init,add,sub,final_state}
#S = {0,1,2,3,4,5,6,7,8,9,+}
#G = {0,1,2,3,4,5,6,7,8,9,+,_}
#q0 = init
#F = {final_state}
#B = _
#N = 1
#D init 3 2 r add
#D add + + r add
#D add 6 7 l sub
#D add 7 8 l sub
#D add 8 9 l sub
#D sub + + l sub
#D sub 2 1 r add
#D sub 1 0 r add
#D sub 0 _ * final_state
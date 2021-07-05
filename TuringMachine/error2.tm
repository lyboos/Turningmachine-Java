#Q = {init, A, B,z}
; Error: 3
#F = {f,z}
; Error: 4
#S = {a,b,_,c,d}
; Error: 5
; Error: 6
#G = {c,d,e,f,g}
#q0 = init
#N = 1
#B = _
; Error: 8
; Error: 8
#D init a _ r A
; Error: 8
; Error: 8
; Error: 7
#D init _ _ * f
; Error: 8
; Error: 8
#D init b _ r B
; Error: 8
; Error: 8
#D A a a r A
; Error: 8
; Error: 8
#D A b a r B
; Error: 8
; Error: 8
; Error: 7
#D A _ a * f
; Error: 8
; Error: 8
#D B b b r B
; Error: 8
; Error: 8
; Error: 7
#D B _ b * f
; Error: 8
; Error: 8
#D B a b r A
#D A c d r A
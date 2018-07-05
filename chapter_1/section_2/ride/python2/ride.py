"""
ID: mcokzoo1
LANG: PYTHON2
TASK: ride
"""
with open('ride.in', 'r') as fin, open('ride.out', 'w') as fout:
	group = 1
	comet = 1
	for c in fin.readline():
		group *= ord(c)-64
	for c in fin.readline():
		comet *= ord(c)-64
	fout.write('GO\n') if (group-comet) % 47 == 0 else fout.write('STAY\n')

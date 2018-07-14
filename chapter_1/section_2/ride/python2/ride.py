'''
ID: mcokzoo1
LANG: PYTHON2
TASK: ride
'''

def eval_name(s):
	p = 1
	for c in s:
		p *= ord(c)-64
	return p

if __name__ == '__main__':
	with open('ride.in', 'r') as fin, open('ride.out', 'w') as fout:
		group = fin.readline()
		comet = fin.readline()
		fout.write('GO\n') if (eval_name(group)-eval_name(comet)) % 47 == 0 else fout.write('STAY\n')

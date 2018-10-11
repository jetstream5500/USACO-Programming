'''
ID: mcokzoo1
LANG: PYTHON2
TASK: milk2
'''


class IntInterval():
	def __init__(self, start, end):
		self.iset = [(4,5),(start, end)]

	def __repr__(self):
		return ' U '.join(map(lambda i: '[%d,%d)' % i, self.iset))

	def union(self, other):
		pass



if __name__ == '__main__':
	with open('milk2.in', 'r') as fin, open('milk2.out', 'w') as fout:
		print(IntInterval(4,10))

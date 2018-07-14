'''
ID: mcokzoo1
LANG: PYTHON2
TASK: beads
'''

def cycle_range(iterable, start=0, end=-1, step=1):
	pool = tuple(iterable)
	n = len(pool)

	start %= n
	end %= n
	step %= n
	c = start
	while start != end:
		yield pool[start]
		start = (start + step) % n
		if c == start:
			break

def count_beads(beads, i, step=1):
	step %= len(beads)
	count = 0
	color = 'w'
	for c in cycle_range(beads, i, i-step, step):
		if color == 'w':
			count += 1
			color = c
		elif c == 'w' or c == color:
			count += 1
		else:
			break

	return count

def measure_split(beads, i):
	countL = count_beads(beads, (i-1) % len(beads), -1)
	countR = count_beads(beads, i)

	sum = countL + countR
	if sum > num_beads:
		sum = num_beads
	return sum

if __name__ == '__main__':
	with open('beads.in', 'r') as fin, open('beads.out', 'w') as fout:
		num_beads = int(fin.readline())
		beads = fin.readline().strip()
		max_split = 0
		for i in range(num_beads):
			sum = measure_split(beads, i)
			if sum > max_split:
				max_split = sum

		fout.write('%d\n' % max_split)

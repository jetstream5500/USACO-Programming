'''
ID: mcokzoo1
LANG: PYTHON2
TASK: gift1
'''
from collections import OrderedDict

def setup_accounts(fin):
	num_people = int(fin.readline().strip())
	bank_accounts = OrderedDict()
	for _ in range(num_people):
		name = fin.readline().strip()
		bank_accounts[name] = 0
	return bank_accounts

def distribute_money(fin, bank_accounts):
	for _ in bank_accounts:
		giver = fin.readline().strip()
		money, num_recievers = map(int,fin.readline().split())
		if num_recievers > 0:
			bank_accounts[giver] += money%num_recievers - money
			for _ in range(num_recievers):
				reciever = fin.readline().strip()
				bank_accounts[reciever] += money/num_recievers

def output_accounts(fin, bank_accounts):
	for name, balance in bank_accounts.items():
		fout.write(name + " %d\n" % balance)

if __name__ == '__main__':
	with open('gift1.in', 'r') as fin, open('gift1.out', 'w') as fout:
		bank_accounts = setup_accounts(fin)
		distribute_money(fin, bank_accounts)
		output_accounts(fout, bank_accounts)

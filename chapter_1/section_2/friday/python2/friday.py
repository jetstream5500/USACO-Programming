'''
ID: mcokzoo1
LANG: PYTHON2
TASK: friday
'''
from functools import total_ordering

@total_ordering
class Date():
	def __init__(self, day, month, year):
		self.day = day
		self.month = month
		self.year = year

	def __repr__(self):
		return 'Date(day=%d, month=%d, year=%d)' % (self.day, self.month, self.year)

	def __str__(self):
		month_names = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
		return month_names[self.month-1] + ' ' + str(self.day) + ', ' + str(self.year)

	def __eq__(self, other):
		return self.year == other.year and self.month == other.month and self.day == other.day

	def __ne__(self, other):
		return not self.__eq__(other)

	def __lt__(self, other):
		if self.year != other.year:
			return self.year < other.year
		elif self.month != other.month:
			return self.month < other.month
		else:
			return self.day < other.day

	@staticmethod
	def is_leap_year(year):
		return (year % 4 == 0 and year % 100 != 0) or year % 400 == 0

	@staticmethod
	def num_days(month, year=2001):
		days_per_month = [31,28,31,30,31,30,31,31,30,31,30,31]
		if month != 2:
			return days_per_month[month-1]
		else:
			return days_per_month[month-1]+int(Date.is_leap_year(year))

	def prev_day(self):
		self.day -= 1
		if self.day < 1:
			self.month -= 1
			if self.month < 1:
				self.year -= 1
				self.month = (self.month+11) % 12 + 1
			self.day = Date.num_days(self.month, self.year)

	def next_day(self):
		self.day += 1
		if Date.num_days(self.month, self.year) < self.day:
			self.day = 1
			self.month += 1
			if self.month > 12:
				self.month = 1
				self.year += 1

	def weekday(self):
		ref = Date(1, 1, 1900)
		weekday = 0
		while self != ref:
			if self < ref:
				ref.prev_day()
				weekday = (weekday+6)%7
			else:
				ref.next_day()
				weekday = (weekday+1)%7
		return weekday

def weekday_distribution(start_date, end_date, day_of_the_month=13):
	weekday_totals = [0,0,0,0,0,0,0]
	weekday = (start_date.weekday()+2) % 7
	while start_date < end_date:
		if start_date.day == day_of_the_month:
			weekday_totals[weekday] += 1
		start_date.next_day()
		weekday = (weekday+1)%7
	return weekday_totals

if __name__ == '__main__':
	with open('friday.in', 'r') as fin, open('friday.out', 'w') as fout:
		years = int(fin.readline())
		start_date = Date(1, 1, 1900)
		end_date = Date(1, 1, 1900+years)

		weekday_totals = weekday_distribution(start_date, end_date, 13)
		fout.write(' '.join(map(str, weekday_totals)) + '\n')

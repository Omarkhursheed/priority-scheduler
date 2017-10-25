import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
import matplotlib.patches as patches

fig1 = plt.figure()
ax1 = fig1.add_subplot(111, aspect='equal')
ax1.set_xlim([0,35])
ax1.set_ylim([0,35])
with open("preemptiveAnswers.txt") as f:
    content = f.readlines()
# you may also want to remove whitespace characters like `\n` at the end of each line

content = [x.strip() for x in content] 
#firstend = content[0].split(',')[2]
rectcorner = 0
rectLength = 0
print(content)
#print(x)
x = len(content)
i = 0
prevProcessName = content[0].split(',')[0]
originTime1 = content[0].split(',')[1]
while(i < x):
	if(content[i].split(',')[0] == prevProcessName):
		rectLength+=1
		if(i == x-1):
			rectLength-=1
			p = ax1.add_patch(patches.Rectangle(
			(5+rectcorner,12),
			rectLength,
			2,
			fill = False))
			left = 5+rectcorner
			width = rectLength
			bottom = 12
			height = 2
			right = left + width
			ax1.text((left+right)/2,13,prevProcessName,horizontalalignment='center',verticalalignment='center')
			ax1.text(left,11,originTime1,horizontalalignment='center',verticalalignment='center')	
			rectcorner+=rectLength
			ax1.text(right,11,content[i].split(',')[1],horizontalalignment='center',verticalalignment='center')	
			
	else:
		p = ax1.add_patch(patches.Rectangle(
			(5+rectcorner,12),
			rectLength,
			2,
			fill = False))
		left = 5+rectcorner
		width = rectLength
		bottom = 12
		height = 2
		right = left + width
		ax1.text((left+right)/2,13,prevProcessName,horizontalalignment='center',verticalalignment='center')
		ax1.text(left,11,originTime1,horizontalalignment='center',verticalalignment='center')

		rectcorner+=rectLength
		rectLength = 1
		prevProcessName = content[i].split(',')[0]	
		originTime1 = content[i].split(',')[1]
	i+=1


"""print(content[0].split(',')[0])
prevProcessName = content[0].split(',')[0]
x = len(content)
i = 0
while(i < x):
	currentProcessName = content[i].split(',')[0]
	string = currentProcessName
	print(currentProcessName)
	rectLength = 1
	while (string == currentProcessName and i < x):
		rectLength+=1
		i+=1
		string = content[i].split(',')[0]
	p = ax1.add_patch(patches.Rectangle(
			(5+rectcorner,12),
			rectLength,
			2,
			fill = False))
	left = 5+rectcorner
	width = rectLength
	bottom = 12
	height = 2
	right = left + width
	ax1.text((left+right)/2,13,currentProcessName,horizontalalignment='center',verticalalignment='center')
	rectcorner+=rectLength"""

"""for line in content:
	listOfThings = line.split(',')
	currentProcessName = listOfThings[0]
	if(currentProcessName == prevProcessName):
		rectLength+=1
	else:
		p = ax1.add_patch(patches.Rectangle(
			(5+rectcorner,12),
			rectLength,
			2,
			fill = False))
		left = 5+rectcorner
		width = rectLength
		bottom = 12
		height = 2
		right = left + width
		ax1.text((left+right)/2,13,listOfThings[0],horizontalalignment='center',verticalalignment='center')
		

		rectLength = 1
	prevProcessName = listOfThings[0]
	if int(firstend) != int(listOfThings[2]):
		emptyRectLength = int(listOfThings[1])-int(firstend)
		print(listOfThings[1])
		print(firstend)
		p = ax1.add_patch(patches.Rectangle(
			(5+rectcorner,12),
			emptyRectLength,
			2,
			fill = False,
			)
		)
		left = 5+rectcorner
		width = emptyRectLength
		bottom = 12
		height = 2
		right = left + width
		top = bottom + height
		ax1.text((left+right)/2,13,listOfThings[0],horizontalalignment='center',verticalalignment='center')
		ax1.text(left,11,firstend,horizontalalignment='center',verticalalignment='center')
		rectcorner+=emptyRectLength
	

	processName = listOfThings[0]	
	rectLength = (int(listOfThings[2])-int(listOfThings[1]))
	p =ax1.add_patch(patches.Rectangle(
			(5+rectcorner, 12),
			rectLength,
			2,
			fill = False,
			)
		)
	left = 5+rectcorner
	width = rectLength
	bottom = 12
	height = 2
	right = left + width
	top = bottom + height
	ax1.text((left+right)/2,13,listOfThings[0],horizontalalignment='center',verticalalignment='center')
	ax1.text(left,11,listOfThings[1],horizontalalignment='center',verticalalignment='center')

	

	#firstend = listOfThings[2]	
	rectcorner+=rectLength
	"""
plt.show()
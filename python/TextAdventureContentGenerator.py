# Running this will prompt you for all information necessary to build the game text.  Note that writing to file will 
# append ALL nodes and choices to the end of the file.  If you wish to flush the current list of nodes and choices,
# please exit and reexecute the program.

import sys

node_list = []
choice_list = []
popup_list = []
previously_used_nodes = []
previously_used_popups = []

def request_yesno_input(query):
	# try:
	yesno = input(query + " [Y/N]").upper()
	# except
	if ((yesno != "Y") & (yesno != "N")):
		print("Please repond with either Y or N")
		return request_yesno_input(query)
	elif (yesno == "N"):
		return False
	elif (yesno == "Y"):
		return True

def request_int_input(query, base=0, ceiling=float("inf"), nullable=False):
    #Asks the question
    if (nullable):
        next_input=input(query + " or [N] if none required: ")
    else:
        next_input=input(query + ":  ")

    #Processes the response, re-asks the question if input is invalid
    if (nullable and next_input.upper() == "N"):
        return -1
    else:
        try:
            next_input = int(next_input)
        except ValueError:
            print("You've entered an invalid value, try again")
            return request_int_input(query, base, ceiling, nullable)
        
    if (next_input > base and next_input < ceiling):
            return next_input
    else:
            print("You've entered an invalid value, try again")
            return request_int_input(query, base, ceiling, nullable)

def requestInputType():
	print("")
	next_type = request_int_input("Are you inputting a node[1], a choice[2], or a popup[3], or are you finished inputting data[4]?", ceiling=5)
	if (next_type == 1):
		get_node_values()
	elif (next_type == 2):
		get_choice_values()
	elif (next_type == 3):
		get_popup_values()
	elif (next_type == 4):
		finished_inputting()
		
def value_or_n(query):
	value = input(query + " (N for none) :")
	if (value.upper() == "N"):
		value = "NULL"
	return value;
	
def is_this_correct(type):
	print("")
	verify_correct = input("Is this correct? [Y/N]").upper()
	if ((verify_correct != "Y") & (verify_correct != "N")):
		print("Please repond with either Y or N")
		is_this_correct(type)
		return
	elif (verify_correct == "N"):
		print("Values deleted")
		return False
	else:
		print(str(type) + " data saved")
		return True
		
class Node_Class(object):
	number = int
	text = str
	image = str
	animation = str
	def get_values(self):
		tempNum = request_int_input("Which node is this?")
		if (tempNum not in previously_used_nodes):
			self.number = tempNum
		else:
			print("You have already written this node.  Please enter another number.")
			return self.get_values()
		self.text = input("What is the node text? :")
		self.image = value_or_n("What is the node image?")
		self.animation = value_or_n("What is the node animation?")
		print("These are the currently saved values")
		print("Text: ", self.text)
		if (self.image == "NULL"):
			print("NO IMAGE")
		else:
			print("Image: ", self.image)
		if (self.animation == "NULL"):
			print("NO ANIMATION")
		else:
			print("Animation: ", self.animation)
		if (is_this_correct("Node")):
			node_list.append(self)
			previously_used_nodes.append(self.number)
		else:
			# self.get_values();
			return requestInputType()
	def __str__(self):
		temp_string = "Node " + str(self.number)
		return(temp_string)
	def __repr__(self):
		return self.__str__()
			
def get_node_values():
	set_more = True
	while (set_more):
	 	newNode = Node_Class()
 	 	# get_node_number calls following methods on its own
	 	newNode.get_values()
	 	print("")
	 	if (request_yesno_input("Would you like to enter more Nodes?")  == False):
	 		set_more = False
	requestInputType()

class Choice_Class(object):
	text = str
	nodeId = int
	connectedNode = int
	connectedFailPopup = int
	itemRequired = int
	itemImproves = int
	testType = int
	testDifficulty = int
	def get_values(self):
		self.nodeId = request_int_input("At which node will this be displayed?")
		self.connectedNode = request_int_input("To which node will this take you?");
		self.connectedSuccessPopup = request_int_input("If successful, to which popup will this take you?", nullable=True);
		self.connectedFailPopup = request_int_input("If a failure, to which popup will this take you?", nullable=True);
		self.text = input("What is the choice text? :")
		self.itemRequired = request_int_input("What item type is required?", nullable=True);
		self.testType = request_int_input("What type of test does this use? Strength[1], Agility[2], or Comradery[3]", ceiling=4, nullable=True);
		if (self.testType != -1):
			self.testDifficulty = request_int_input("What is the test difficulty?");
			self.itemImproves = request_int_input("What item type improves this?", nullable=True);
		else:
			self.testDifficulty = -1
			self.itemImproves = -1
		print("These are the currently saved values")
		print("NodeID: ", str(self.nodeId))
		print("ConnectedNode: ", str(self.connectedNode))
		print("ConnectedFailPopup: ", str(self.connectedFailPopup))
		print("Text: ", self.text)
		if (self.itemRequired == -1):
			print("NO ITEM REQUIRED")
		else:
			print("ItemRequired: ", str(self.itemRequired))
		if (self.testType == -1):
			print("NO TEST REQUIRED")
		else:
			print("TestType: ", str(self.testType))
			print("TestDifficulty: ", str(self.testDifficulty))
			if (self.itemImproves == -1):
				print("NO ITEM IMPROVES")
			else:
				print("ItemImproves: ", str(self.itemImproves))
		if (is_this_correct("Choice")):
			choice_list.append(self)
		else:
			# self.get_values();
			return requestInputType()
	def __str__(self):
		temp_string = "Node " + str(self.number)
		return(temp_string)
	def __repr__(self):
		return self.__str__()
		
def get_choice_values():
	set_more = True
	while (set_more):
	 	newChoice = Choice_Class()
 	 	# get_node_number calls following methods on its own
	 	newChoice.get_values()
	 	print("")
	 	if (request_yesno_input("Would you like to enter more Choices?")  == False):
	 		set_more = False
	requestInputType()

class Popup_Class(object):
	number = int
	text = str
	image = str
	animation = str
	def get_values(self):
		tempNum = request_int_input("Which popup is this?")
		if (tempNum not in previously_used_popups):
			self.number = tempNum
		else:
			print("You have already written this popup.  Please enter another number.")
			return self.get_values()
		self.text = input("What is the popup text? :")
		self.image = value_or_n("What is the popup image?")
		self.animation = value_or_n("What is the popup animation?")
		print("These are the currently saved values")
		print("Text: ", self.text)
		if (self.image == "NULL"):
			print("NO IMAGE")
		else:
			print("Image: ", self.image)
		if (self.animation == "NULL"):
			print("NO ANIMATION")
		else:
			print("Animation: ", self.animation)
		if (is_this_correct("Popup")):
			popup_list.append(self)
			previously_used_popups.append(self.number)
		else:
			# self.get_values();
			return requestInputType()
	def __str__(self):
		temp_string = "Popup " + str(self.number)
		return(temp_string)
	def __repr__(self):
		return self.__str__()

def get_popup_values():
	set_more = True
	while (set_more):
	 	newPopup = Popup_Class()
 	 	# get_node_number calls following methods on its own
	 	newPopup.get_values()
	 	print("")
	 	if (request_yesno_input("Would you like to enter more popups?")  == False):
	 		set_more = False
	requestInputType()
	
def clean_text_for_java():
	for node in node_list:
		node.text = clean_string(node.text)
		print("node.text: " + node.text)
		
def clean_string(dirtyString):
	dirtyString = dirtyString.replace("\"", r"\"")
	print("dirtyString" + dirtyString)
	return dirtyString
	
def write_to_file():
	clean_text_for_java()
	print("")
	print("Writing the following text to file:")
	print("")
	print("Nodes")
	total_string = "\n"
	total_string += "Nodes\n"
	for node in node_list:
		write_string = "new String[] {\"" + str(node.number) + "\", \"" + node.text + "\", \"" + node.image + "\", \"" + node.animation + "\"},"
		total_string += write_string
		total_string += "\n"
		print(write_string)
	print("")
	print("Choices")
	total_string += "\n"
	total_string += "Choices\n"
	for choice in choice_list:
		write_string = "new ChoiceData(\"" + choice.text + "\", " + str(choice.nodeId) + ", " + str(choice.connectedNode) + ", " + str(choice.connectedSuccessPopup)  + ", " + str(choice.connectedFailPopup) + ", " + str(choice.itemRequired) + ", " + str(choice.itemImproves) + ", " + str(choice.testType) + ", " + str(choice.testDifficulty) + "),"
		total_string += write_string
		total_string += "\n"
		print(write_string)
	print("")
	print("Popups")
	total_string += "\n"
	total_string += "Popups\n"
	for popup in popup_list:
		write_string = "new String[] {\"" + str(popup.number) + "\", \"" + popup.text + "\", \"" + popup.image + "\", \"" + popup.animation + "\"},"
		total_string += write_string
		total_string += "\n"
		print(write_string)
	print("\n")
	total_string += "\n\n\n"
	file = open('java_inputs.txt', 'a')
	file.write(total_string)
	file.close()
	sys.exit()
	# main_loop()
	
def main_loop():
	requestInputType()   #this will call getNodeValues / getChoiceValues / getPopupValues

def finished_inputting():
	print("")
	print("Printing current data")
	for node in node_list:
		print("Node " + str(node.number) + ": " + node.text[:30])
	for choice in choice_list:
		print("Choice: " + choice.text[:30])
	for popup in popup_list:
		print("Popup: " + popup.text[:30])
	if (request_yesno_input("Would you like to write these to file?")):
		write_to_file()
	else: 
		main_loop()

main_loop()

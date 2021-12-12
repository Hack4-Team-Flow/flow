import api


engine = pyttsx3.init()

selected_order_type = {'takeorder':[{'food_type': None, 'food_kind': None, 'drink': None}]}



def speak(text):
    engine.setProperty("rate", 125)
    engine.say(text)
    engine.runAndWait()
    engine.stop()

def command_loop_drink():
    send_order_type()

def command_loop_food():
    selected_order_type['food_type'] = 'burger'
    selected_order_type['food_kind'] = 'mushroom'
    selected_order_type['drink'] = 'cola'
    command_loop_drink()

def send_order_type():
    api.main(selected_order_type)


if __name__ =="__main__":
    command_loop_food()

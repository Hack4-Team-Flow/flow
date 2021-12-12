from flask import Flask, jsonify, request
import csv

data_stores = [{'store':[{'name':'White Bakery', 'duration': 5, 'price':30, 'latitude':37.0202875421039, 'longtitude':30.5981990104282,'businessType':'bake'}]},
            {'store':[{'name':'McDonalds', 'duration':15, 'price':23, 'latitude':36.886827290282, 'longtitude':30.7025303036082,'businessType':'burger'}]},
            {'store':[{'name': 'Beach Bar', 'duration':45, 'price':55, 'latitude':36.6137941584317, 'longtitude':30.5610624029243,'businessType':'drink'}]},
            {'store':[{'name':'Coffee Shop', 'duration':10, 'price':5, 'latitude':36.9964701515979, 'longtitude':30.8528972730965,'businessType':'coffee'}]},
            {'store':[{'name':'Burger King', 'duration':17, 'price':21, 'latitude':36.8867220621227, 'longtitude':30.8187125270902,'businessType':'burger'}]},
            {'store':[{'name':"Maria's Coffee", 'duration':10, 'price':18, 'latitude':36.8513376776303,'longtitude':30.8505665675635,'businessType':'coffee'}]}]

def read_car_csv():    
    with open("data_car.csv", "r") as file:
        data = file.read()
    rows = data.split("\n")
    data_car_dict = {}
    for row in rows:
        info = row.split(",")
    order_id = int(info[0])
    order_type = info[1]
    latitude = float(info[2])
    longtitude = float(info[3])
    data_car_dict[order_id] = {"order_type":order_type,
                                "latitude":latitude,
                                "longtitude":longtitude}
    return data_car_dict


app = Flask(__name__)

@app.route('/')
def index():
    return "Welcome to our app"

@app.route('/takeorder', methods = ['POST'])
def post():
    if request.method == 'POST':
        #data = request.form[example_json]
        return jsonify(read_car_csv())

@app.route('/stores', methods= ['POST','GET'])
def post_store():
    if request.method == 'POST':
        return jsonify(data_stores)
    elif request.method =='GET':
        return jsonify(data_stores)



if __name__ == "__main__":
    #read_data(data_stores)
    #read_data(data_json)
    app.run()
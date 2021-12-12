from flask import Flask, jsonify, request
import pandas as pd

data_car_dict = {'location':[{'latitude': 36.9159143629179, 'longtitude': 30.804464229398153}]}
selected_order_type = {'takeorder':[{'food_type': 'Burger', 'food_kind': 'Mushroom', 'drink': 'Cola'}]}
data_stores = [{'store':[{'name':'White Bakery', 'duration': 5, 'price':30, 'latitude':37.0202875421039, 'longtitude':30.5981990104282,'businessType':'bake'}]},
            {'store':[{'name':'McDonalds', 'duration':15, 'price':23, 'latitude':36.886827290282, 'longtitude':30.7025303036082,'businessType':'burger'}]},
            {'store':[{'name': 'Beach Bar', 'duration':45, 'price':55, 'latitude':36.6137941584317, 'longtitude':30.5610624029243,'businessType':'drink'}]},
            {'store':[{'name':'Coffee Shop', 'duration':10, 'price':5, 'latitude':36.9964701515979, 'longtitude':30.8528972730965,'businessType':'coffee'}]},
            {'store':[{'name':'Burger King', 'duration':17, 'price':21, 'latitude':36.8867220621227, 'longtitude':30.8187125270902,'businessType':'burger'}]},
            {'store':[{'name':"Maria's Coffee", 'duration':10, 'price':18, 'latitude':36.8513376776303,'longtitude':30.8505665675635,'businessType':'coffee'}]}]

app = Flask(__name__)

@app.route('/')
def index():
    return "Welcome to our app"

@app.route('/takeorder', methods = ['GET'])
def post():
    if request.method == 'GET':
        return jsonify(selected_order_type)

@app.route('/stores', methods= ['GET'])
def post_store():
    if request.method == 'GET':
        return jsonify(data_stores)

@app.route('/location', methods = ['GET'])
def post_location():
    if request.method == 'GET':
        return jsonify(data_car_dict)

def main():
    app.run()

if __name__ == "__main__":
    main()
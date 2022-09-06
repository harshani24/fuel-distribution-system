import React,{useEffect, useState} from 'react';
import Order from "./order.component";
import axios from "axios";

import Spinner from './common/Spinner';

const OrderList = (props) => {
    console.log("Order List");

    const [orders , setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    const [user, setUser] = useState('P00001');

    const fetchData = () => {
        console.log(user);
        axios.get(`http://localhost:8191/orders/getMyOrders/${user}`)
              .then( res => {
                  console.log(res.data)
                  setOrders(res.data);
                  setLoading(false);
              })
              .catch(err => console.log(err))

       // fetch('http://localhost:8191/orders', {
        //   method: 'get',
        // })
        //   .then((response) => {
        //     console.log(response.json());
        //     //return response.json();
        //   })
        //   .then((data) => {
        //     console.log(data);
        //     setExercies(data);
        //     setLoading(false);
        //   });

    };

    useEffect( () =>{
        fetchData();
    },[]);

    const ordersList =  () => {
        return orders.map(order => {
            return(<Order order={order} orderId={order.id} orderConfirm={confirmOrderReceived} key={order.id}/>)
        })
    }

   
   const confirmOrderReceived = (id) => {
    axios.put(`http://localhost:8191/orders/receivedConfirm`,{ id : id })
        .then(res => console.log(res))
        .catch(err => console.log(err))
    }

    return (
        <div>
            {loading ? <div><Spinner/></div> : 
                <div style={{outlineStyle:"solid", width:"95%" ,height:"100%", padding:"10px 30px"}}>
                <h3>View All Orders(user-> {user})</h3>

                <div className="table-responsive">
                    <table className="table  table-bordered border-dark table-hover">
                        <thead className="table-dark"> 
                            <tr>
                                <th>Order ID</th>
                                <th>User</th>
                                <th>Station</th>
                                <th>Octane92(L)</th>
                                <th>Octane95(L)</th>
                                <th>Auto Diesel(L)</th>
                                <th>Super Diesel(L)</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {ordersList()}
                        </tbody>
                    </table>
                </div>
            
                </div>
            }

        </div>
    );

}

export default OrderList; 
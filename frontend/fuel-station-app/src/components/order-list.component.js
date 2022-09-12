import React,{useEffect, useState} from 'react';
import Order from "./order.component";
import axios from "axios";

import Spinner from './common/Spinner';

const OrderList = (props) => {
    console.log("Order List");

    const [orders , setOrders] = useState([]);
    const [loading, setLoading] = useState(true);
    const [dispatchedMsg , setDispatchedMsg] = useState(false);
    const [orderId , setOrderId] = useState('');

    const [user, setUser] = useState('P00001');


    useEffect( () =>{
        fetchData();
    },[]);

    const fetchData = () => {
        console.log(user);
        // axios.get(`http://localhost:8191/orders/getMyOrders/${user}`)
        //       .then( res => {
        //           console.log(res.data)
        //           setOrders(res.data);
        //           setLoading(false);
        //       })
        //       .catch(err => console.log(err))

        axios.get(`http://localhost:8191/orders/`)
              .then( res => {
                  console.log(res.data)
                  setOrders(res.data);
                  setLoading(false);
              })
              .catch(err => console.log(err))

    };

    const ordersList =  () => {
        return orders.map(order => {
            return(<Order order={order} orderId={order.id} orderConfirm={confirmOrderReceived} key={order.id}/>)
        })
    }

   
   const confirmOrderReceived = (id) => {
        axios.put(`http://localhost:8191/orders/receivedConfirm`,{ id : id })
            .then(res =>{
                console.log(res)
                setDispatchedMsg(true);
                setOrderId(id);
            })
            .catch(err => console.log(err))
    
    }
 
    return (
        <div>
            {loading ? <div><Spinner/></div> : 
                <div style={{outlineStyle:"solid", width:"95%" ,height:"100%", padding:"10px 20px"}}>
                {
                    dispatchedMsg ? <div class="alert alert-primary" role="alert">
                      Confirm the Completion of Order '{orderId}' !!!
                    </div> : null
                }

                <h2>View All Orders(user-> {user})</h2>

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
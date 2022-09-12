import React from 'react';
import { Link } from 'react-router-dom';
 
const Order = (props) => {
    return (
        <tr>
            <td>{props.order.id}</td>
            <td>{props.order.passport}</td>
            <td>{props.order.station}</td>
            <td>{props.order.quantityOctane92}</td>
            <td>{props.order.quantityOctane95}</td>
            <td>{props.order.quantityAutoDiesel}</td>
            <td>{props.order.quantitySuperDiesel}</td>
            <td style={{"font-weight":"bold", "color":"red"}}>{props.order.status}</td>
            <td style={{width: "350px"}}>
            <Link className="btn btn-secondary" to={"/view-order/"+props.orderId} style={{ width: "150px", marginRight: "5px" }}  > View Status</Link> 
            {props.order.status === 'dispatched' ?<a href='/' className="btn btn-primary" style={{ width: "150px" }}  onClick={() => props.orderConfirm(props.order.id)}> Received Confirm</a> : null} 
            </td>
        </tr>
    ); 

}

export default Order;
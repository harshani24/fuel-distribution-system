import React , {useEffect, useState} from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const ViewOrder = () => {
    const { id } = useParams();

    const [order, setOrder] = useState({});

    const [rejected , setRejected] = useState(false);

    const [allocated , setAllocated] = useState(false);
    const [scheduled , setScheduled] = useState(false);
    const [dispatched , setDispatched] = useState(false);
    const [completed , setCompleted] = useState(false);

 

    useEffect(() =>{
        getOrder();  
    },{order});

    const getOrder = () =>{
        axios.get('http://localhost:8191/orders/' + id)
        .then(res => {
            setStatus(res.data.status);
            setOrder(res.data);
            console.log(res.data);
        })
        .catch(err => console.log(err));
    }

    const setStatus = (status) => {
        switch(status){
            case 'allocated': setAllocated(true);
                              break;

            case 'scheduled': setAllocated(true);
                              setScheduled(true);
                              break;

            case 'dispatched':setAllocated(true);
                              setScheduled(true);
                              setDispatched(true);
                              break;

            case 'completed': setAllocated(true);
                              setScheduled(true);
                              setDispatched(true);
                              setCompleted(true);
                              break;
            
            case 'rejected' : setRejected(true);
                              break;
            default: 
        }
    }

    return (
        <div style={{outlineStyle:"solid", width:"75%" ,height:"1120px", padding:"30px 30px"}}>
            <h2>Order Details -> {id}</h2><br/><br/>

            <form>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'> <h6> Passport Number: </h6></label>

                    <div className="col-sm-4">
                    <input type='text'readOnly className='form-control'value={order.passport} />
                    </div>

                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Station Name: </h6> </label>

                    <div className="col-sm-4">
                    <input type ='text' readOnly className='form-control' value={order.station} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Current Status: </h6> </label>

                    <div className="col-sm-4">
                    <input type ='text' readOnly className='form-control' value={order.status} />
                    </div>
                </div>
                <br/>
                <br/>

                <h5>Ordered Fuel Quantities</h5><br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Octane92(L): </h6> </label>

                    <div className="col-sm-4">
                    <input type='number' readOnly className='form-control' value={order.quantityOctane92} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Octane95(L): </h6> </label> 

                    <div className="col-sm-4">
                    <input type='number' readOnly className='form-control' value={order.quantityOctane95} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Auto Diesel(L): </h6> </label>

                    <div className="col-sm-4">
                    <input type='number' readOnly className='form-control' value={order.quantityAutoDiesel} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Super Diesel(L): </h6> </label>
                    <div className="col-sm-4">
                    <input type='number' readOnly className='form-control' value={order.quantitySuperDiesel} />
                    </div>
                </div>
                <br/>
                <br/>

                <h5>Order Status and Dates</h5><br/>
                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Ordered Date: </h6> </label>
                    <div className="col-sm-4">
                    <input readOnly className='form-control' value={order.orderedTime} />
                    </div>
                </div>
                <br/>

                {allocated ?  <div><div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Allocated Date: </h6> </label>
                    <div className="col-sm-4">
                    <input readOnly className='form-control' value={order.allocatedTime} />
                    </div> <br/></div>
                </div>: null }
       

                {scheduled ? <div> <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Scheduled Date: </h6> </label>
                    <div className="col-sm-4">
                    <input readOnly className='form-control' value={order.scheduledTime} />
                    </div><br/></div>
                </div> : null}

                {dispatched ? <div> <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Dispatched Date: </h6> </label>
                    <div className="col-sm-4">
                    <input readOnly className='form-control' value={order.dispatchedTime} />
                    </div><br/></div>
                </div> :null}

                {completed ?<div> <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Completed Date: </h6> </label>
                    <div className="col-sm-4">
                    <input readOnly className='form-control' value={order.completedTime} />
                    </div><br/></div>
                </div>: null}

                {rejected ? <div><div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Rejected Date: </h6> </label>
                    <div className="col-sm-4">
                    <input readOnly className='form-control' value={order.rejectedTime} />
                    </div><br/></div>
                </div>: null}
               <br/>

            </form>

        </div>
    );

}

export default ViewOrder;
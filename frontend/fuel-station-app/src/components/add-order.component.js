import React, { useState } from 'react';
import axios from 'axios';

import {FaPlusCircle} from 'react-icons/fa';

const AddOrder = () => {
   console.log("Add Order");

   const [passport, setPassport] = useState('');
   const [station, setStation] = useState('');

   const [octane92, setOctane92] = useState(false);
   const [quantityOctane92 , setQuantityOctane92] = useState(0);
   const [octane95, setOctane95] = useState(false);
   const [quantityOctane95 , setQuantityOctane95] = useState(0);
   const [autoDiesel, setAutoDiesel] = useState(false);
   const [quantityAutoDiesel , setQuantityAutoDiesel] = useState(0);
   const [superDiesel, setSuperDiesel] = useState(false);
   const [quantitySuperDiesel , setQuantitySuperDiesel] = useState(0);

   const onSubmit = (e) =>{ 
        e.preventDefault();

        const order = {
            passport : passport,
            station : station,
            octane92 : octane92,
            quantityOctane92 : quantityOctane92,
            octane95 : octane95,
            quantityOctane95 : quantityOctane95,
            autoDiesel : autoDiesel,
            quantityAutoDiesel : quantityAutoDiesel,
            superDiesel : superDiesel,
            quantitySuperDiesel : quantitySuperDiesel
        }

        console.log(order);
 
        axios.post("http://localhost:8191/orders" , order)
             .then(res => console.log(res.data))
             .catch(err => console.log(err));

        window.location = '/';

   }

    return (
        <div style={{outlineStyle:"solid", width:"75%" ,height:"100%", padding:"30px 40px"}}>
            <h2> Add Order</h2> <br/>

            <form onSubmit={onSubmit}>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'> <h6> Passport Number</h6></label>

                    <div className="col-sm-4">
                    <input type='text' required className='form-control' name='passport' value={passport} onChange={e => setPassport(e.target.value)} />
                    </div>

                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Station Name</h6> </label>

                    <div className="col-sm-4">
                    <input type ='text' required className='form-control' name='station' value={station} onChange={e => setStation(e.target.value)} />
                    </div>
                </div>
                <br/>
                <br/>

                <h5>Fuel Quantity</h5>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Octane92(L)</h6> </label>

                    <div className="col-sm-4">
                    <input type='number' min='0' className='form-control' name='octane92' value={quantityOctane92} 
                    onChange={e => {
                        setQuantityOctane92(e.target.value); 
                        if(e.target.value> 0) {
                            setOctane92(true);
                        }
                        }} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Octane95(L)</h6> </label> 

                    <div className="col-sm-4">
                    <input type='number' min='0'  className='form-control' name='octane95' value={quantityOctane95} 
                    onChange={e => {
                        setQuantityOctane95(e.target.value); 
                        if(e.target.value> 0) {
                            setOctane95(true);
                        }
                        }} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Auto Diesel(L)</h6> </label>

                    <div className="col-sm-4">
                    <input type='number'  min='0'  className='form-control' name='autoDiesel' value={quantityAutoDiesel} 
                    onChange={e => {
                        setQuantityAutoDiesel(e.target.value); 
                        if(e.target.value> 0) {
                            setAutoDiesel(true);
                        }
                        }} />
                    </div>
                </div>
                <br/>

                <div className='form-group row'>
                    <label className='col-sm-2 col-form-label'><h6> Super Diesel(L)</h6> </label>
                    <div className="col-sm-4">
                    <input type='number'  min='0'  className='form-control' name='superDiesel' value={quantitySuperDiesel}
                    onChange={e => {
                        setQuantitySuperDiesel(e.target.value); 
                        if(e.target.value> 0) {
                            setSuperDiesel(true);
                        }
                        }} />
                    </div>
                </div>
                <br/>

                <div className='form-group'>
                    <button type="submit" className="btn btn-primary" style={{width:"30%" , padding:"10px 40px"}}><FaPlusCircle />  Make Order</button>
                </div>

            </form>
        </div>
    );
 
}

export default AddOrder;
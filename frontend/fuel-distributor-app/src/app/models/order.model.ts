export interface Order{
    id : string;
    passport : string;
    station : string;
    octane92 : boolean;
    quantityOctane92 : number;
    octane95 : boolean;
    quantityOctane95: number;
    autoDiesel : boolean;
    quantityAutoDiesel : number;
    superDiesel : boolean;
    quantitySuperDiesel : number;
    ordered :boolean;
    orderedTime: Date;
    allocated : boolean;
    allocatedTime : Date;
    scheduled : boolean;
    scheduledTime : Date; 
    dispatched : boolean;
    dispatchedTime : Date;
    completed : boolean;
    completedTime : Date;
    rejected : boolean;
    rejectedTime : Date;
    status : string;
    statusDate : Date;
}


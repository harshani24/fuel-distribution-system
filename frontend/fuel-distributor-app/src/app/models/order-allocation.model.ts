export interface OrderAllocation {
    id : string;
    orderId : string;
    allocatedDateTime: Date;
    dispatchedDateTime : Date;
    allocatedOctane92: number;
    dispatchedOctane92 : number;
    statusOctane92 : string;
    allocatedOctane95: number;
    dispatchedOctane95 : number;
    statusOctane95 : string;
    allocatedAutoDiesel: number;
    dispatchedAutoDiesel : number;
    statusAutoDiesel : string;
    allocatedSuperDiesel: number;
    dispatchedSuperDiesel : number;
    statusSuperDiesel : string;
}
 
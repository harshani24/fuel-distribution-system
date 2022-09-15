export interface Stock{
    id : string;
    orderId : string;
    orderAllocationId: string;
    allocatedDateTime: Date;
    dispatchedDateTime : Date;
    availableOctane92 : number;
    allocatedOctane92: number;
    dispatchedOctane92 : number;
    statusOctane92 : string;
    availableOctane95 : number;
    allocatedOctane95: number;
    dispatchedOctane95 : number;
    statusOctane95 : string;
    availableAutoDiesel : number;
    allocatedAutoDiesel: number;
    dispatchedAutoDiesel : number;
    statusAutoDiesel : string;
    availableSuperDiesel : number;
    allocatedSuperDiesel: number;
    dispatchedSuperDiesel : number;
    statusSuperDiesel : string;
}


/// <reference types="mongoose/types/aggregate" />
/// <reference types="mongoose/types/callback" />
/// <reference types="mongoose/types/collection" />
/// <reference types="mongoose/types/connection" />
/// <reference types="mongoose/types/cursor" />
/// <reference types="mongoose/types/document" />
/// <reference types="mongoose/types/error" />
/// <reference types="mongoose/types/expressions" />
/// <reference types="mongoose/types/helpers" />
/// <reference types="mongoose/types/middlewares" />
/// <reference types="mongoose/types/indexes" />
/// <reference types="mongoose/types/models" />
/// <reference types="mongoose/types/mongooseoptions" />
/// <reference types="mongoose/types/pipelinestage" />
/// <reference types="mongoose/types/populate" />
/// <reference types="mongoose/types/query" />
/// <reference types="mongoose/types/schemaoptions" />
/// <reference types="mongoose/types/schematypes" />
/// <reference types="mongoose/types/session" />
/// <reference types="mongoose/types/types" />
/// <reference types="mongoose/types/utility" />
/// <reference types="mongoose/types/validation" />
/// <reference types="mongoose/types/virtuals" />
/// <reference types="mongoose/types/inferschematype" />
import { Document } from 'mongoose';
export declare type DispatchDocument = Dispatch & Document;
export declare class Dispatch {
    orderId: string;
    station: string;
    passport: string;
    octane92: boolean;
    quantityOctane92: number;
    octane95: boolean;
    quantityOctane95: number;
    autoDiesel: boolean;
    quantityAutoDiesel: number;
    superDiesel: boolean;
    quantitySuperDiesel: number;
    scheduledDate: Date;
    dispatchedDate: Date;
}
export declare const DispatchSchema: import("mongoose").Schema<Dispatch, import("mongoose").Model<Dispatch, any, any, any, any>, {}, {}, {}, {}, "type", Dispatch>;

import { Dispatch, DispatchDocument } from './../schemas/Dispatch.schema';
import { Model } from 'mongoose';
export declare class DispatchRepository {
    private dispatchModel;
    constructor(dispatchModel: Model<DispatchDocument>);
    create(dispatch: Dispatch): Promise<Dispatch>;
    findAll(): Promise<Dispatch[]>;
    setDispatchStatus(id: string): Promise<Dispatch>;
}

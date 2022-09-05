import { Dispatch } from './../schemas/Dispatch.schema';
import { DispatchCreateDto } from './../dto/DispatchCreate.dto';
import { DispatchRepository } from './../repository/Dispatch.repository';
export declare class DispatchService {
    private dispatchRepository;
    constructor(dispatchRepository: DispatchRepository);
    getAll(): Promise<Dispatch[]>;
    create(dispatchCreateDto: DispatchCreateDto): Promise<Dispatch>;
    setDispatchStatus(id: string): Promise<Dispatch>;
    setDateValues(d: number[]): Date;
}

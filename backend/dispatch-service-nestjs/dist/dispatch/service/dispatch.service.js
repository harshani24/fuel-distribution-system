"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.DispatchService = void 0;
const Dispatch_schema_1 = require("./../schemas/Dispatch.schema");
const Dispatch_repository_1 = require("./../repository/Dispatch.repository");
const common_1 = require("@nestjs/common");
let DispatchService = class DispatchService {
    constructor(dispatchRepository) {
        this.dispatchRepository = dispatchRepository;
    }
    async getAll() {
        return await this.dispatchRepository.findAll();
    }
    async create(dispatchCreateDto) {
        const dispatch = new Dispatch_schema_1.Dispatch();
        dispatch.orderId = dispatchCreateDto.orderId;
        dispatch.station = dispatchCreateDto.station;
        dispatch.passport = dispatchCreateDto.passport;
        dispatch.octane92 = dispatchCreateDto.octane92;
        dispatch.quantityOctane92 = dispatchCreateDto.quantityOctane92;
        dispatch.octane95 = dispatchCreateDto.octane95;
        dispatch.quantityOctane95 = dispatchCreateDto.quantityOctane95;
        dispatch.autoDiesel = dispatchCreateDto.autoDiesel;
        dispatch.quantityAutoDiesel = dispatchCreateDto.quantityAutoDiesel;
        dispatch.superDiesel = dispatchCreateDto.superDiesel;
        dispatch.quantitySuperDiesel = dispatchCreateDto.quantitySuperDiesel;
        dispatch.scheduledDate = dispatchCreateDto.scheduledDate;
        return await this.dispatchRepository.create(dispatch);
    }
    async setDispatchStatus(id) {
        return await this.dispatchRepository.setDispatchStatus(id);
    }
    setDateValues(d) {
        let year = d[0];
        let month = d[1];
        let date = d[2];
        let hour = d[3];
        let min = d[4];
        let sec = d[5];
        let scheduleDate = new Date(+year, month - 1, +date);
        console.log(scheduleDate);
        return scheduleDate;
    }
};
DispatchService = __decorate([
    (0, common_1.Injectable)(),
    __metadata("design:paramtypes", [Dispatch_repository_1.DispatchRepository])
], DispatchService);
exports.DispatchService = DispatchService;
//# sourceMappingURL=dispatch.service.js.map
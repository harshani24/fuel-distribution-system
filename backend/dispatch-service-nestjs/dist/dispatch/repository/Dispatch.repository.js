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
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.DispatchRepository = void 0;
const Dispatch_schema_1 = require("./../schemas/Dispatch.schema");
const common_1 = require("@nestjs/common");
const mongoose_1 = require("@nestjs/mongoose");
const mongoose_2 = require("mongoose");
let DispatchRepository = class DispatchRepository {
    constructor(dispatchModel) {
        this.dispatchModel = dispatchModel;
    }
    async create(dispatch) {
        let newDispatch = new this.dispatchModel(dispatch);
        return await newDispatch.save();
    }
    async findAll() {
        let list = await this.dispatchModel.find({ dispatchedDate: null });
        console.log('Fetching the available dispatch list');
        return list;
    }
    async setDispatchStatus(id) {
        console.log('Setting order dispatched status ' + id);
        const filter = { orderId: id };
        const update = { dispatchedDate: new Date() };
        const record = await this.dispatchModel.findOneAndUpdate(filter, update, {
            new: true,
        });
        return record;
    }
};
DispatchRepository = __decorate([
    (0, common_1.Injectable)(),
    __param(0, (0, mongoose_1.InjectModel)(Dispatch_schema_1.Dispatch.name)),
    __metadata("design:paramtypes", [mongoose_2.Model])
], DispatchRepository);
exports.DispatchRepository = DispatchRepository;
//# sourceMappingURL=Dispatch.repository.js.map
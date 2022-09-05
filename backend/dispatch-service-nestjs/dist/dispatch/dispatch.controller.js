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
exports.DispatchController = void 0;
const common_1 = require("@nestjs/common");
const dispatch_service_1 = require("./service/dispatch.service");
const microservices_1 = require("@nestjs/microservices");
const kafkaHost = process.env.KAFKA_HOST || 'localhost';
const kafkaPort = process.env.KAFKA_PORT || '9092';
let DispatchController = class DispatchController {
    constructor(dispatchService) {
        this.dispatchService = dispatchService;
    }
    async getAllDispatches() {
        console.log('Fetch all Dispatches');
        return await this.dispatchService.getAll();
    }
    async setOrderStatus(body) {
        this.updateOrderStatus(body.orderId);
        return await this.dispatchService.setDispatchStatus(body.orderId);
    }
    scheduleListener(message) {
        console.log("new message " + JSON.stringify(message));
        message.scheduledDate = this.dispatchService.setDateValues(message.scheduledDate);
        console.log('Create a Dispatch for ' + JSON.stringify(message));
        return this.dispatchService.create(message);
    }
    async onModuleInit() {
        this.client.subscribeToResponseOf('dispatch-complete-topic');
        await this.client.connect();
    }
    async updateOrderStatus(id) {
        this.client.emit('dispatch-complete-topic', id);
    }
};
__decorate([
    (0, common_1.Get)(),
    (0, common_1.UsePipes)(common_1.ValidationPipe),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], DispatchController.prototype, "getAllDispatches", null);
__decorate([
    (0, common_1.Post)(),
    __param(0, (0, common_1.Body)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], DispatchController.prototype, "setOrderStatus", null);
__decorate([
    (0, microservices_1.MessagePattern)('dispatch-topic'),
    __param(0, (0, microservices_1.Payload)()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", void 0)
], DispatchController.prototype, "scheduleListener", null);
__decorate([
    (0, microservices_1.Client)({
        transport: microservices_1.Transport.KAFKA,
        options: {
            client: {
                clientId: 'dispatch-service',
                brokers: [`${kafkaHost}:${kafkaPort}`],
            },
            consumer: {
                groupId: 'dispatch-group',
            },
        },
    }),
    __metadata("design:type", microservices_1.ClientKafka)
], DispatchController.prototype, "client", void 0);
DispatchController = __decorate([
    (0, common_1.Controller)('dispatch'),
    __metadata("design:paramtypes", [dispatch_service_1.DispatchService])
], DispatchController);
exports.DispatchController = DispatchController;
//# sourceMappingURL=dispatch.controller.js.map
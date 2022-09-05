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
exports.DispatchSchema = exports.Dispatch = void 0;
const mongoose_1 = require("@nestjs/mongoose");
let Dispatch = class Dispatch {
};
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", String)
], Dispatch.prototype, "orderId", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", String)
], Dispatch.prototype, "station", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", String)
], Dispatch.prototype, "passport", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Boolean)
], Dispatch.prototype, "octane92", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Number)
], Dispatch.prototype, "quantityOctane92", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Boolean)
], Dispatch.prototype, "octane95", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Number)
], Dispatch.prototype, "quantityOctane95", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Boolean)
], Dispatch.prototype, "autoDiesel", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Number)
], Dispatch.prototype, "quantityAutoDiesel", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Boolean)
], Dispatch.prototype, "superDiesel", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Number)
], Dispatch.prototype, "quantitySuperDiesel", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Date)
], Dispatch.prototype, "scheduledDate", void 0);
__decorate([
    (0, mongoose_1.Prop)(),
    __metadata("design:type", Date)
], Dispatch.prototype, "dispatchedDate", void 0);
Dispatch = __decorate([
    (0, mongoose_1.Schema)()
], Dispatch);
exports.Dispatch = Dispatch;
exports.DispatchSchema = mongoose_1.SchemaFactory.createForClass(Dispatch);
//# sourceMappingURL=Dispatch.schema.js.map
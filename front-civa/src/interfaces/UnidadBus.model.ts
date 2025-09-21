import { ComercialBus } from "./ComercialBus.model";
import { ModeloBus } from "./ModeloBus.model";

export interface UnidadBus {
  id: number;
  numero_bus: number;
  placa: string;
  fecha: string;
  caracteristicas: ComercialBus;
  marca_bus: ModeloBus;
  status: string;
}

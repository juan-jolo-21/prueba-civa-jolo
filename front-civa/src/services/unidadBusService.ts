import { API_BASE_URL } from "../config/api";
import { UnidadBus } from "../interfaces/UnidadBus.model";

export async function getUnidadesBus():Promise<UnidadBus[]> {
  const response = await fetch(`${API_BASE_URL}/bus`);
  if (!response.ok) {
    throw new Error("Error al obtener unidades de bus");
  }
  return response.json() as Promise<UnidadBus[]>;
}

export async function getUnidadBusById(id: number): Promise<UnidadBus> {
  const response = await fetch(`${API_BASE_URL}/unidades/${id}`);
  if (!response.ok) {
    throw new Error("Error al obtener la unidad de bus");
  }
  return response.json() as Promise<UnidadBus>;
}
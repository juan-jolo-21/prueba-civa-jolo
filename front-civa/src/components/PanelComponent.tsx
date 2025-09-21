import './PanelComponent.css'
import { useEffect, useState } from "react";
import { getUnidadesBus,  } from "../services/unidadBusService"
import { UnidadBus } from '../interfaces/UnidadBus.model';
import { TableComponent } from './TableComponent';

//export const PanelComponent = ({buses}:{buses: Array<Object>}) => {
export const PanelComponent = () => {
    const [unidades, setUnidades] = useState<UnidadBus[]>([]);

    useEffect(() => {
        getUnidadesBus().then(setUnidades).catch(console.error);
    }, []);


    

    return (
        <div className='flex-column flex-wrap  '>
            <h1>Lista de buses CIVA</h1>
            <TableComponent BusData={unidades}/>
        </div>
    )
}

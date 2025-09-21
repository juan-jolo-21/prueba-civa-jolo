
import { UnidadBus } from '../interfaces/UnidadBus.model'

export const TableComponent = ({ BusData }: { BusData: UnidadBus[] }) => {


    const formatMonth = (dateString: string) => {
        const date = new Date(dateString);
        const nombresMeses = [
            'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
            'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
        ];
        const dia = date.getDay();
        const mes = nombresMeses[date.getMonth()];
        const año = date.getFullYear();
        return `${dia} de ${mes} del ${año}`;
    };


    return (

        <div className='TableComponent'>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Padrón</th>
                        <th scope="col">Placa</th>
                        <th scope="col">Status</th>
                        <th scope="col">Caracteristicas</th>
                        <th scope="col">Marca del bus</th>
                        <th scope="col">Detalles</th>
                    </tr>
                </thead>
                <tbody>
                    {BusData.map((item, index) => {
                        const rowClass = item.status === "inactivo" ? "bg-danger-subtle" : "bg-primary-subtle";
                        return (
                            <tr key={index} className="align-middle">
                                <td className={rowClass}>{item.numero_bus}</td>
                                <td className={rowClass}>{item.placa}</td>
                                <td className={rowClass}>{item.status}</td>
                                <td className={rowClass}>{item.caracteristicas.branding}</td>
                                <td className={rowClass}>{item.marca_bus.fabricante}</td>
                                <td className={rowClass}>
                                    <>
                                        {/* <!-- Button trigger modal --> */}
                                        <button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                            Ver Detalles
                                        </button>

                                        {/* <!-- Modal --> */}
                                        <div className="modal fade" id="exampleModal" tabIndex={-1} aria-labelledby="exampleModalLabel" aria-hidden="true">
                                            <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                                                <div className="modal-content">
                                                    <div className="modal-header">
                                                        <h3 className="modal-title fs-5" id="exampleModalLabel">Detalles del bus</h3>
                                                        <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div className="modal-body">
                                                        <pre style={{ margin: 0, textAlign: 'left', width: '100%' }}>

                                                            
                                                            <h5>Datos generales</h5>
                                                            <p><strong>Padrón de bus:</strong> {item.numero_bus}</p>
                                                            <p><strong>Placa:</strong> {item.placa}</p>
                                                            <p><strong>Fecha de incorporación:</strong> {formatMonth(item.fecha)}</p>
                                                            <p><strong>Status:</strong> {item.status}</p>
                                                            <br/>
                                                            <h5>Características</h5>
                                                            <p><strong>Branding:</strong> {item.caracteristicas.branding}</p>
                                                            <p><strong>Asientos:</strong> {item.caracteristicas.asientos}</p>
                                                            <p><strong>Detalles:</strong> {item.caracteristicas.detalles}</p>
                                                            <br/>
                                                            <h5>Modelo</h5>
                                                            <p><strong>Fabricante:</strong> {item.marca_bus.fabricante}</p>
                                                            <p><strong>Modelo:</strong> {item.marca_bus.modelo}</p>
                                                        </pre>
                                                    </div>
                                                    <div className="modal-footer">
                                                        <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </>

                                </td>
                            </tr>
                        )
                    }
                    )}
                </tbody>
            </table>
        </div>

    )

}

import axios from "axios"
import { useEffect, useState } from "react"

const MyServiceRequests = () => {

    const myServiceRequestsApi = "http://localhost:8080/api/service-requests/my"

    const [serviceRequests, setServiceRequests] = useState([])

    const config_details = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    const loadServiceRequests = async () => {
        try {
            const response = await axios.get(myServiceRequestsApi, config_details)
            setServiceRequests(response.data)
        }
        catch (err) { }
    }

    useEffect(() => {
        loadServiceRequests()
    }, [])

    const getStatusBadgeClass = (status) => {
        switch (status) {
            case 'OPEN': return 'badge-status-open'
            case 'IN_PROGRESS': return 'badge-status-inprogress'
            case 'RESOLVED': return 'badge-status-resolved'
            default: return 'bg-secondary'
        }
    }

    return (
        <div className="row">
            <div className="col-lg-12">
                <div className="card">
                    <div className="card-header">
                        My Service Requests
                    </div>
                    <div className="card-body">
                        <div className="table-responsive">
                        <table className="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Request ID</th>
                                    <th>Asset</th>
                                    <th>Issue Description</th>
                                    <th>Status</th>
                                    <th>Created At</th>
                                    <th>Updated At</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    serviceRequests.map((req, index) => (
                                        <tr key={req.serviceRequestId}>
                                            <td>{index + 1}</td>
                                            <td>{req.serviceRequestId}</td>
                                            <td>{req.assetName}</td>
                                            <td>{req.issueDescription}</td>
                                            <td>
                                                <span className={`badge ${getStatusBadgeClass(req.serviceStatus)} px-2 py-1`}>
                                                    {req.serviceStatus}
                                                </span>
                                            </td>
                                            <td>{req.createdAt ? new Date(req.createdAt).toLocaleDateString() : "-"}</td>
                                            <td>{req.updatedAt ? new Date(req.updatedAt).toLocaleDateString() : "-"}</td>
                                        </tr>
                                    ))
                                }
                                {
                                    serviceRequests.length === 0 ?
                                        <tr><td colSpan="7" className="text-center text-muted">No service requests found</td></tr> : ""
                                }
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default MyServiceRequests

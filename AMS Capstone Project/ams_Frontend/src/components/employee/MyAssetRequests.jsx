import axios from "axios"
import { useEffect, useState } from "react"

const MyAssetRequests = () => {

    const myRequestsApi = "http://localhost:8080/api/asset-requests/my"

    const [requests, setRequests] = useState([])

    const config_details = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    const loadRequests = async () => {
        try {
            const response = await axios.get(myRequestsApi, config_details)
            setRequests(response.data)
        }
        catch (err) { }
    }

    useEffect(() => {
        loadRequests()
    }, [])

    const getStatusBadgeClass = (status) => {
        switch (status) {
            case 'PENDING': return 'badge-status-pending'
            case 'APPROVED': return 'badge-status-approved'
            case 'REJECTED': return 'badge-status-rejected'
            default: return 'bg-secondary'
        }
    }

    return (
        <div className="row">
            <div className="col-lg-12">
                <div className="card">
                    <div className="card-header">
                        My Asset Requests
                    </div>
                    <div className="card-body">
                        <div className="table-responsive">
                        <table className="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Request ID</th>
                                    <th>Asset</th>
                                    <th>Reason</th>
                                    <th>Status</th>
                                    <th>Created At</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    requests.map((req, index) => (
                                        <tr key={req.requestId}>
                                            <td>{index + 1}</td>
                                            <td>{req.requestId}</td>
                                            <td>{req.assetName}</td>
                                            <td>{req.reason}</td>
                                            <td>
                                                <span className={`badge ${getStatusBadgeClass(req.requestStatus)} px-2 py-1`}>
                                                    {req.requestStatus}
                                                </span>
                                            </td>
                                            <td>{req.createdAt ? new Date(req.createdAt).toLocaleDateString() : "-"}</td>
                                        </tr>
                                    ))
                                }
                                {
                                    requests.length === 0 ?
                                        <tr><td colSpan="6" className="text-center text-muted">No requests found</td></tr> : ""
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

export default MyAssetRequests

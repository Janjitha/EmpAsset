import axios from "axios"
import { useEffect, useState } from "react"

const PendingAudits = () => {

    const getMyAuditsApi = "http://localhost:8080/api/audits/my"

    const [audits, setAudits] = useState([])
    const [successMsg, setSuccessMsg] = useState()
    const [errMsg, setErrMsg] = useState()

    const config_details = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    const loadMyAudits = async () => {
        try {
            const response = await axios.get(getMyAuditsApi, config_details)
            setAudits(response.data)
        }
        catch (err) { }
    }

    useEffect(() => {
        loadMyAudits()
    }, [])

    const verifyAudit = async (id) => {
        try {
            const response = await axios.put(`http://localhost:8080/api/audits/${id}/verify`, {}, config_details)
            setSuccessMsg("Asset verified successfully")
            setErrMsg(undefined)
            // update status in state — same soft-update pattern as ManageAssetRequests
            setAudits(audits.map(a => a.id === id ? response.data : a))
        }
        catch (err) {
            setErrMsg("Failed to verify: " + (err.response?.data?.message || ""))
            setSuccessMsg(undefined)
        }
    }

    const rejectAudit = async (id) => {
        try {
            const response = await axios.put(`http://localhost:8080/api/audits/${id}/reject`, {}, config_details)
            setSuccessMsg("Audit marked as rejected")
            setErrMsg(undefined)
            setAudits(audits.map(a => a.id === id ? response.data : a))
        }
        catch (err) {
            setErrMsg("Failed to reject: " + (err.response?.data?.message || ""))
            setSuccessMsg(undefined)
        }
    }

    const getStatusBadgeClass = (status) => {
        switch (status) {
            case 'PENDING': return 'bg-warning bg-opacity-10 text-warning border border-warning'
            case 'VERIFIED': return 'bg-success bg-opacity-10 text-success border border-success'
            case 'REJECTED': return 'bg-danger bg-opacity-10 text-danger border border-danger'
            default: return 'bg-secondary'
        }
    }

    const pendingCount = audits.filter(a => a.status === 'PENDING').length

    return (
        <div className="row">
            <div className="col-lg-12">

                <div className="card">
                    <div className="card-header">
                        <span>My Asset Audits</span>
                        {pendingCount > 0 &&
                            <span className="badge bg-warning text-dark">
                                {pendingCount} pending
                            </span>
                        }
                    </div>
                    <div className="card-body">

                        {successMsg !== undefined ?
                            <div className="alert alert-primary mb-4">{successMsg}</div> : ""}
                        {errMsg !== undefined ?
                            <div className="alert alert-danger mb-4">{errMsg}</div> : ""}

                        <div className="table-responsive">
                        <table className="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Asset Name</th>
                                    <th>Asset Model</th>
                                    <th>Status</th>
                                    <th>Sent At</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {audits.map((audit, index) => (
                                    <tr key={audit.id}>
                                        <td>{index + 1}</td>
                                        <td>{audit.assetName}</td>
                                        <td>{audit.assetModel || "-"}</td>
                                        <td>
                                            <span className={`badge ${getStatusBadgeClass(audit.status)} px-2 py-1`}>
                                                {audit.status}
                                            </span>
                                        </td>
                                        <td>{audit.sentAt ? new Date(audit.sentAt).toLocaleDateString() : "-"}</td>
                                        <td>
                                            {audit.status === 'PENDING' ? (
                                                <div className="d-flex gap-2">
                                                    <button className="btn btn-sm btn-outline-success"
                                                        onClick={() => verifyAudit(audit.id)}>
                                                        <i className="bi bi-check-circle me-1"></i>Verify
                                                    </button>
                                                    <button className="btn btn-sm btn-outline-danger"
                                                        onClick={() => rejectAudit(audit.id)}>
                                                        <i className="bi bi-x-circle me-1"></i>Reject
                                                    </button>
                                                </div>
                                            ) : (
                                                <span className="text-muted" style={{ fontSize: '0.83rem' }}>
                                                    {audit.status === 'VERIFIED' ? 'Verified' : 'Rejected'} on{' '}
                                                    {audit.verifiedAt ? new Date(audit.verifiedAt).toLocaleDateString() : "-"}
                                                </span>
                                            )}
                                        </td>
                                    </tr>
                                ))}
                                {audits.length === 0 ?
                                    <tr><td colSpan="6" className="text-center text-muted">No audit requests found</td></tr> : ""}
                            </tbody>
                        </table>
                        </div>

                    </div>
                </div>

            </div>
        </div>
    )
}

export default PendingAudits

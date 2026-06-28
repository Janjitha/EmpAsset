import axios from "axios"
import { useEffect, useState } from "react"

const MyAllocations = () => {

    const myAllocationsApi = "http://localhost:8080/api/asset-allocations/my"

    const [allocations, setAllocations] = useState([])
    const [successMsg, setSuccessMsg] = useState()
    const [errMsg, setErrMsg] = useState()

    const config_details = {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem('token')
        }
    }

    const loadAllocations = async () => {
        try {
            const response = await axios.get(myAllocationsApi, config_details)
            setAllocations(response.data)
        }
        catch (err) { }
    }

    useEffect(() => {
        loadAllocations()
    }, [])

    const returnAsset = async (allocationId) => {
        if (!window.confirm("Are you sure you want to return this asset?")) return
        try {
            await axios.put(`http://localhost:8080/api/asset-allocations/${allocationId}/return`, {}, config_details)
            setSuccessMsg("Asset returned successfully")
            setErrMsg(undefined)
            loadAllocations()
        }
        catch (err) {
            setErrMsg("Return failed: " + (err.response?.data?.message || ""))
            setSuccessMsg(undefined)
        }
    }

    return (
        <div className="row">
            <div className="col-lg-12">
                <div className="card">
                    <div className="card-header">
                        My Allocations
                    </div>
                    <div className="card-body">
                        {
                            successMsg !== undefined ?
                                <div className="alert alert-primary mb-4">{successMsg}</div> : ""
                        }
                        {
                            errMsg !== undefined ?
                                <div className="alert alert-danger mb-4">{errMsg}</div> : ""
                        }
                        <div className="table-responsive">
                        <table className="table table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Allocation ID</th>
                                    <th>Asset</th>
                                    <th>Model</th>
                                    <th>Allocated At</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    allocations.map((alloc, index) => (
                                        <tr key={alloc.allocationId}>
                                            <td>{index + 1}</td>
                                            <td>{alloc.allocationId}</td>
                                            <td>{alloc.assetName}</td>
                                            <td>{alloc.assetModel || "-"}</td>
                                            <td>{alloc.allocatedAt ? new Date(alloc.allocatedAt).toLocaleDateString() : "-"}</td>
                                            <td>
                                                {
                                                    alloc.returned ?
                                                        <span className="badge badge-status-resolved px-2 py-1">Returned</span> :
                                                        <span className="badge badge-status-allocated px-2 py-1">Active</span>
                                                }
                                            </td>
                                            <td>
                                                {
                                                    !alloc.returned ?
                                                        <button className="btn btn-sm btn-outline-warning"
                                                            onClick={() => returnAsset(alloc.allocationId)}>
                                                            <i className="bi bi-box-arrow-left me-1"></i>Return
                                                        </button> :
                                                        <span className="text-muted fs-7">
                                                            Returned on {alloc.returnedAt ? new Date(alloc.returnedAt).toLocaleDateString() : "-"}
                                                        </span>
                                                }
                                            </td>
                                        </tr>
                                    ))
                                }
                                {
                                    allocations.length === 0 ?
                                        <tr><td colSpan="7" className="text-center text-muted">No allocations found</td></tr> : ""
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

export default MyAllocations

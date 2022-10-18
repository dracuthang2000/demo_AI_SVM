import React, { useEffect, useState } from "react";
import './CreateProduct.css'
import {
    TextField
    , Avatar
    , Autocomplete
    , Box
    , Button,
    Switch,
    FormControlLabel,
    FormGroup,
    Select,
    MenuItem,
    InputLabel,
    FormControl,
    FormHelperText,
    Table,
} from "@mui/material";
import Axios from "../../Axios";
import { useNavigate, useParams } from "react-router-dom";
import { Add } from "@mui/icons-material";


const CreateProduct = () => {
    const initialMessageError = {
        name: '',
        ram: '',
        cpu: '',
        resolution1: '',
        resolution2: '',
        price: '',
        brand: '',
        image: '',
        widescreen: '',
        rom: '',
        dataTrain: ''
    }
    const initialError = {
        name: false,
        ram: false,
        cpu: false,
        resolution1: false,
        resolution2: false,
        price: false,
        image: false,
        brand: false,
        widescreen: false,
        rom: false,
        dataTrain: false
    }
    const option = [{
        value: 1, label: 'Data1'
    }, {
        value: 2, label: 'Data2'
    }]
    const navigate = useNavigate();
    const [error, setError] = useState(initialError);
    const [product, setProduct] = useState<any>('')
    const [lstCpu, setLstCpu] = useState([] as any);
    const [brands, setBrands] = useState([] as any)
    const [messageError, setMessageError] = useState(initialMessageError);
    const [img, setImg] = useState<any | null>('');
    const [submit, setSubmit] = useState(false);
    const [dataTrain, setDataTrain] = useState('');
    const handleImage = (e: any) => {
        const reader = new FileReader();
        reader.onload = () => {
            if (reader.readyState === 2) {
                setImg(URL.createObjectURL(e.target.files[0]));
                console.log(e.target.files[0]);
                setProduct({ ...product, binary_image: reader.result, image: Date.now() + e.target.files[0].name });
                // setTest({ image_byte: reader.result, image_name: e.target.files[0].name });
            }
        }
        reader.readAsDataURL(e.target.files[0])
    }
    const handleCancel = () => {
        navigate(-1);
    }
    const valid = () => {
        let flag = false;
        let errorMessage = {} as any;
        let error1 = initialError;
        if (product.name === null || product.name === undefined || product.name === '') {
            errorMessage.name = 'Name is not null';
            error1.name = true;
            flag = true;
        } else {
            error1.name = false;
        }

        if (product?.brand === undefined || product?.brand === null) {
            errorMessage.brand = 'Brand is not null';
            error1.brand = true;
            flag = true;
        } else {
            error1.brand = false;
        }
        if (product?.cpu === undefined || product?.cpu === null) {
            errorMessage.cpu = 'CPU is not null';
            error1.cpu = true;
            flag = true;
        } else {
            error1.cpu = false;
        }

        if (product.ram === undefined || product.ram === null || product.ram === '') {
            errorMessage.ram = 'Ram is not null';
            error1.ram = true;
            flag = true;
        } else {
            error1.ram = false;
        }

        if (product.rom === undefined || product.rom === null || product.rom === '') {
            errorMessage.rom = 'Rom is not null';
            error1.rom = true;
            flag = true;
        } else {
            error1.rom = false;
        }

        if (product.widescreen === undefined || product.widescreen === null || product.widescreen === '') {
            errorMessage.widescreen = 'Widescreen is not null';
            error1.widescreen = true;
            flag = true;
        } else {
            error1.widescreen = false;
        }

        if (product.resolution1 === undefined || product.resolution1 === null || product.resolution1 === '') {
            errorMessage.resolution1 = 'Resolution1 is not null';
            error1.resolution1 = true;
            flag = true;
        } else {
            error1.resolution1 = false;
        }
        if (product.resolution2 === undefined || product.resolution2 === null || product.resolution2 === '') {
            errorMessage.resolution2 = 'Resolution2 is not null';
            error1.resolution2 = true;
            flag = true;
        } else {
            error1.resolution2 = false;
        }

        if (product.price === undefined || product.price === 0 || product.price === null || product.price === '') {
            errorMessage.price = 'Price is not null';
            error1.price = true;
            flag = true;
        } else if (product.price < 0) {
            errorMessage.price = 'Price is not less then zero';
            error1.price = true;
            flag = true;
        } else {
            error1.price = false;
        }

        if (product.image === undefined || product.image === null || product.image === '') {
            errorMessage.image = 'Image is not null';
            error1.image = true;
            flag = true;
        } else {
            error1.image = false;
        }
        if (dataTrain === undefined || dataTrain === null || dataTrain === '') {
            errorMessage.dataTrain = 'Please choose data train';
            error1.dataTrain = true;
            flag = true;
        } else {
            error1.dataTrain = false;
        }
        setMessageError(errorMessage);
        setError(error1);
        return flag;
    }
    const postCreateProduct = () => {
        Axios.post("phone/save", product)
            .then((res) => {
                alert(res.data);
                navigate(-1);
            })
            .catch((error) => {
                console.log(error);
            })
    }
    useEffect(() => {
        console.log(messageError);
        if (Object.keys(messageError).length === 0 && submit) {
            postCreateProduct();
        }
    }, [messageError])

    const handleCreate = () => {
        valid();
        setSubmit(true);
    }

    useEffect(() => {
        Axios.get(`phone/getListCPU`)
            .then(res => {
                setLstCpu(res.data);
                console.log(res.data);

            }).catch((error) => {
                console.log(error);

            })
    }, [])
    useEffect(() => {
        Axios.get(`phone/getListBrand`)
            .then(res => {
                setBrands(res.data);
            }).catch((error) => {
                console.log(error);
            })
    }, [])
    const filterCpu = () => {

        if (product.cpu !== null && product.cpu !== undefined) {
            for (var i in lstCpu) {
                if (product.cpu.id === lstCpu[i].id) {
                    return lstCpu[i];
                }
            }
        }
        return null;
    }
    const filterBrand = () => {
        if (product.brand !== null && product.brand !== undefined) {
            for (var i in brands) {
                if (product.brand.id === brands[i].id) {
                    return brands[i];
                }
            }
        }
        return null;
    }
    const handleChange = (e: any) => {
        const name = e.target.name;
        const value = e.target.value;
        setProduct({ ...product, [name]: value });
    }
    return (
        <div className="container">
            <div className="updateClothesContainer">
                <div className="header">
                    <h4>
                        {'New Product'}
                    </h4>
                    <hr />
                </div>
                <div className="main">
                    <div className="left">
                        <div className="container-input">
                            <TextField sx={{ width: '100%' }}
                                onChange={handleChange}
                                error={error.name}
                                value={`${product.name ? product.name : ''}`}
                                label={'Name *'}
                                name="name" />
                            {
                                error.name && <FormControl error variant="standard">
                                    <FormHelperText id="component-error-text">{messageError.name}</FormHelperText>
                                </FormControl>
                            }

                        </div>
                        <div className="container-input">
                            <Autocomplete
                                disablePortal
                                id="cpu"
                                options={lstCpu}
                                value={filterCpu()}
                                getOptionLabel={(option) => option.cpu_name}
                                onChange={(event: any, newInputValue: any) => {
                                    setProduct({ ...product, cpu: newInputValue });
                                }}
                                sx={{ width: '100%' }}
                                renderInput={(params) => <TextField
                                    {...params}
                                    error={error.cpu}
                                    label="CPU *" />}
                            />
                            {error.cpu && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.cpu}</FormHelperText>
                            </FormControl>}
                        </div>
                        <div className="container-input">
                            <Autocomplete
                                disablePortal
                                id="brand"
                                options={brands}
                                value={filterBrand()}
                                getOptionLabel={(option) => option.brand_name}
                                onChange={(event: any, newInputValue: any) => {
                                    setProduct({ ...product, brand: newInputValue });
                                }}
                                sx={{ width: '100%' }}
                                renderInput={(params) => <TextField
                                    {...params}
                                    error={error.brand}
                                    label="Brand *" />}
                            />
                            {error.brand && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.brand}</FormHelperText>
                            </FormControl>}
                        </div>
                        <div className="container-input">
                            <TextField
                                name="ram"
                                value={`${product.ram ? product.ram : ''}`}
                                onChange={handleChange}
                                label={'Ram *'}
                                type={'text'}
                                error={error.ram}
                            />
                            {error.ram && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.ram}</FormHelperText>
                            </FormControl>}
                        </div>
                        <div className="container-input">
                            <TextField
                                name="rom"
                                value={`${product.rom ? product.rom : ''}`}
                                onChange={handleChange}
                                label={'Rom *'}
                                type={'text'}
                                error={error.rom}
                            />
                            {error.rom && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.rom}</FormHelperText>
                            </FormControl>}
                        </div>
                        <div className="container-input">
                            <Table>
                                <tr>
                                    <td style={{ width: "40%" }}>
                                        <TextField
                                            name="resolution1"
                                            value={`${product.resolution1 ? product.resolution1 : ''}`}
                                            onChange={handleChange}
                                            label={'Resolution1 *'}
                                            type={'number'}
                                            error={error.resolution1}
                                        />
                                        {error.resolution1 && <FormControl error variant="standard">
                                            <FormHelperText id="component-error-text">{messageError.resolution1}</FormHelperText>
                                        </FormControl>}
                                    </td>
                                    <td style={{ textAlign: 'center', width: "20%", verticalAlign: 'middle' }}>
                                        <h4>X</h4>
                                    </td>
                                    <td style={{ textAlign: 'right', width: "40%" }}>
                                        <TextField
                                            name="resolution2"
                                            value={`${product.resolution2 ? product.resolution2 : ''}`}
                                            onChange={handleChange}
                                            label={'Resolution2 *'}
                                            type={'number'}
                                            error={error.resolution2}
                                        />
                                        {error.resolution2 && <FormControl error variant="standard">
                                            <FormHelperText id="component-error-text">{messageError.resolution2}</FormHelperText>
                                        </FormControl>}
                                    </td>
                                </tr>
                            </Table>
                        </div>
                        <div className="container-input">
                            <TextField
                                name="widescreen"
                                value={`${product.widescreen ? product.widescreen : ''}`}
                                onChange={handleChange}
                                label={'Widescreen (inch) *'}
                                type={'number'}
                                error={error.widescreen}
                            />
                            {error.widescreen && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.widescreen}</FormHelperText>
                            </FormControl>}

                        </div>
                        <div className="container-input">
                            <TextField
                                name="price"
                                value={`${product.price}`}
                                onChange={handleChange}
                                label={'Price *'}
                                type={'number'}
                                error={error.price}
                            />
                            {error.price && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.price}</FormHelperText>
                            </FormControl>}

                        </div>
                        <div className="container-input">
                            <FormControl sx={{ width: 250 }}>
                                <InputLabel id="demo-select-small">Choose data train</InputLabel>
                                <Select
                                    labelId="demo-select-small"
                                    id="demo-select-small"
                                    value={dataTrain}
                                    error={error.dataTrain}
                                    label="Choose data train"
                                    onChange={(e) => setDataTrain(e.target.value)}
                                >
                                    <MenuItem value="">
                                        <em>None</em>
                                    </MenuItem>
                                    {option.map(data => <MenuItem value={data.value}>{data.label}</MenuItem>)}
                                </Select>
                                {error.dataTrain && <FormControl error variant="standard">
                                    <FormHelperText id="component-error-text">{messageError.dataTrain}</FormHelperText>
                                </FormControl>}
                            </FormControl>
                        </div>
                    </div>
                    <div className="right">
                        <div className="imageContainer">
                            <div className="image">
                                {<img style={{ borderColor: `${error.image && 'red'}` }} src={img ? img : require('../../image/frame.png')} />}
                            </div>
                            {error.image && <FormControl error variant="standard">
                                <FormHelperText id="component-error-text">{messageError.image}</FormHelperText>
                            </FormControl>}
                        </div>
                        <div className="btnContainer">
                            <Button variant="outlined" component="label">
                                Choose
                                <input
                                    style={{ display: 'none' }}
                                    onChange={handleImage}
                                    accept="image/*"
                                    multiple type="file" />
                            </Button>
                        </div>
                    </div>
                </div>
                <div className="btnContainer">
                    <Button sx={{ width: '150px' }} variant="outlined" onClick={handleCancel}>Cancel</Button>
                    <Button sx={{ width: '150px' }} variant="outlined" onClick={handleCreate}>Create new</Button>
                </div>
            </div>
        </div>
    )
}

export default CreateProduct;
import React, { useState, useEffect, useRef } from 'react';
import { Container } from 'react-bootstrap';
import CardItem from '../CardItem/CardItem';
import Axios from '../../Axios';
import './ScreenCard.css';
import { Link, useNavigate } from 'react-router-dom';
import { Avatar, Button, FormControl, InputLabel, MenuItem, Select, Table } from '@mui/material';
import { Add, Filter, FilterAlt, RestartAlt } from '@mui/icons-material';
import { AnyNsRecord } from 'dns';

const ProductScreen = (props: any) => {
    const option = [
        { value: "A", label: "Bình dân" },
        { value: "B", label: "Tầm trung" },
        { value: "C", label: "Cao cấp" }];
    const navigate = useNavigate();
    const [products, setProducts] = useState([] as any);
    const [filter, setFilter] = useState('');
    const [loading, setLoading] = useState(true);
    useEffect(() => {
        if (loading) {
            Axios.get('phone/getListPhone')
                .then((res) => {
                    const listProduct = res.data;
                    setProducts(
                        listProduct.map((p: any) => {
                            return {
                                id: p.id,
                                img: p.image,
                                product_name: p.name,
                                price: p.price,
                                label: p.label
                            };
                        })
                    );
                    setLoading(false);
                })
                .catch((error) => {
                    console.log(error);
                });
        }
    }, [loading]);
    const handleClickFilter = () => {
        if (filter === "") {
            setLoading(true);
        } else {
            Axios.get(`phone/findPhoneByLabel`, {
                params: {
                    filter: filter
                }
            }).then(res => {
                const listProduct = res.data;
                setProducts(
                    listProduct.map((p: any) => {
                        return {
                            id: p.id,
                            img: p.image,
                            product_name: p.name,
                            price: p.price,
                            label: p.label
                        };
                    })
                );
            }).catch(e => {
                console.log(e);
            })
        }

    }
    const handleClickReset = () => {
        setLoading(true);
        setFilter("");
    }
    const handleClickAdd = () => {
        navigate("/create");
    }
    return (
        <Container>
            <Container>
                <div className='container-header'>
                    <div className="left">
                        <FormControl sx={{ m: 1, width: 250 }}>
                            <InputLabel id="demo-select-small">Filter</InputLabel>
                            <Select
                                labelId="demo-select-small"
                                id="demo-select-small"
                                value={filter}
                                label="Label"
                                onChange={(e) => setFilter(e.target.value)}
                            >
                                <MenuItem value="">
                                    <em>None</em>
                                </MenuItem>
                                {option.map(data => <MenuItem value={data.value}>{data.label}</MenuItem>)}
                            </Select>
                        </FormControl>
                        <FormControl sx={{ m: 1, width: 50 }}>
                            <Button onClick={handleClickFilter} variant='outlined' sx={{ height: '55px' }}><FilterAlt /></Button>
                        </FormControl>
                        <FormControl sx={{ m: 1, width: 50 }}>
                            <Button onClick={handleClickReset} variant='outlined' sx={{ height: '55px' }}><RestartAlt /></Button>
                        </FormControl>
                    </div>
                    <div className="right">
                        <FormControl sx={{ m: 1, width: 50 }}>
                            <Button onClick={handleClickAdd} variant='outlined' sx={{ height: '55px' }}><Add /></Button>
                        </FormControl>
                    </div>
                </div>
                <div className='container clearfix'>
                    <h4 className='float-start'>PRODUCT</h4>
                </div>
                <div className="wrapper-item">
                    {products.map((item: any) => (
                        <CardItem
                            item={item}
                        />
                    ))}
                </div>
            </Container>
        </Container >
    );
}

export default ProductScreen;
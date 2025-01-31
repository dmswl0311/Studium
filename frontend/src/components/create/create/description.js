import React, { Component } from 'react';
import './description.css';
import { BrowserRouter as Router, Route, Switch, Link } from 'react-router-dom';
import axios from "axios";
//import { BrowserRouter, Route } from 'react-router-dom';
//import { Navbar, Nav, Form, FormControl, Button } from 'react-bootstrap';
import Swal from 'sweetalert2'

 class description extends Component{

    constructor(props){
      super(props);
      this.state = {
        inputarea:"",
        btnColorState : false,

        location: this.props.location.state.location,
        selectedTopics : this.props.location.state.selectedTopics,
        studyName : this.props.location.state.studyName
      };
      this.handleRequest = this.handleRequest.bind(this);
    }

    sendStudyData = (e) =>{
      this.opensweetalert();
      this.handleRequest();
    }

    handleChange = (e) =>{
      var textlength = e.target.value.length;
      this.setState({
        inputarea:e.target.value
      })
      console.log(textlength);
    }

    

    handleRequest = async () =>{
      const response = await axios({
        method: 'post',
        url: 'http://localhost:8080/create-study.do',
        data: {
          studyLocation : this.state.location,
          studyTag : String(this.state.selectedTopics),
          studyName : this.state.studyName,
          studyDescription : this.state.inputarea
        }
      });
      console.log(response);
    }

  

  opensweetalert(){
    Swal.fire({
      title: '생성 완료',
      text: "축하합니다! 스터디 생성이 완료되었습니다",
      type: 'success',
    })
  }

  opensweetalertdanger()
  {
    Swal.fire({
      title: 'Please Check ',
      text: "OOPS",
      type: 'warning',
    })
  }


   render() {
    const {location, selectedTopics, studyName} = this.state;
    console.log(location, selectedTopics, studyName);
    return(
      <div className='description creat-study'>
        <div className="description-row-step">
          <div classNam="description-stepbox">
              <p id="description-step">4/4단계</p>
          </div>
          <form className="description-contents-all">
            <div className="description-contents-box">
              <h2>&#127881;마지막으로 그룹에 관한 설명을 작성해 주세요&#127881;</h2>
              <div className="description-contents-sub">
                <p id="description-subbox">설명글은 회원들에게 그룹을 홍보할 때 표시됩니다. <br />
                변경사항이 있다면 나중에 언제든지 업데이트가 가능합니다.</p>
              </div>
              <div className="description-contents-wrap">
                <ul className="description-subjectKinds">
                    <li>그룹의 목적은 무엇인가요?</li>
                    <li>어떠한 회원을 모집하나요?</li>
                    <li>이 그룹에선 어떠한 이벤트가 진행되나요?</li>
                </ul>
              </div>
            </div>
            <div>
              <div className="description-inputContainer">
                <form>
                    <textarea value={this.state.inputarea} id="description-textarea" minLength="50" name="inputarea" 
                      onChange={this.handleChange} cols="70" rows="15" placeholder="최소 50자 이상 작성해 주세요." style={{resize: 'none'}} >
                    </textarea>
                </form>
              </div>
              <ul>
                <li className="description-text-error" >필수사항</li>
              </ul>
            </div>
          </form>
          
          <div className="description-Footer">
            <div className="description-Footer-box">
              <Link to="../">
                <button className="save-btn creat-button2" disabled={this.state.inputarea.length<50} onClick={this.sendStudyData}>생성</button>
                {/* disabled={this.state.inputarea.length<50} */}
              </Link>
            </div>
          </div>
        </div>
      </div>
    );
   }
 }

 export default description;
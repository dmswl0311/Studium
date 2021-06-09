import React from "react";
import { BrowserRouter, Switch, HashRouter, Route } from "react-router-dom"
import Navigation from "./components/navigation/Navigation"
import Footer from "./components/footer/Footer"
import ScrollToTop from './components/ScrollToTop'
import Main from "./routes/main/main"
import EventDetail from "./routes/event-detail/event-detail"
import GroupDetail from "./routes/group-detail/group-detail"
import Search from './routes/search/search'


function App(){
  return (
  <div>
    <BrowserRouter>
      <Navigation />
      <ScrollToTop />
      <Switch>
        <Route path="/" exact={true} component={Main} />
        <Route path="/event-detail" component={EventDetail} />
        <Route path="/group-detail" component={GroupDetail} />
        <Route path="/search" component={Search} />
      </Switch>
      <Footer />
    </BrowserRouter>
  </div>
  )
}

export default App;
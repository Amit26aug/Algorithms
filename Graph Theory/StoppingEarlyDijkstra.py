from queue import PriorityQueue

'''
the following implementation of dijkstra algorithm helps to find the shortest...
distance between two nodes in a graph. We stop as soon as we find the target node.
This is being called as stopping early implementation of dijkstra.
'''

def dijkstra(adj_list, start, end):
    
    dist = [None for i in adj_list]
    dist[start] = 0
    
    visited = [False for i in adj_list]
    
    pq = PriorityQueue()
    pq.put((0, start))
    
    while not pq.empty():
        priority_weight, current = pq.get()
        
        if dist[current] < priority_weight:
            continue
        
        visited[current] = True
        
        for node, weight in adj_list[current]:
            if visited[node]:
                continue
            min_dist = dist[current] + weight
            dist[node] = min_dist if dist[node] == None else min(dist[node], min_dist)
            if min_dist == dist[node]:
                pq.put((dist[node], node))
        if current == end:
            return dist[current]
    return None

#testing

adj_list = [
        [(1, 4), (2, 1)],
        [(3, 1)],
        [(1, 2), (3, 5)],
        [(4, 3)],
        []
    ]

print(dijkstra(adj_list, 0, 4))
        
    

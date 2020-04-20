from queue import PriorityQueue

def dijkstra_shortest_path(adj_list, start_node):
    
    n = len(adj_list)
    
    pq = PriorityQueue()
    
    visited = [False for i in adj_list]
    
    prev = [None for i in adj_list]
    
    dist = [None for i in adj_list]
    dist[start_node] = 0
    
    pq.put((0, start_node))
    
    while not pq.empty():
        min_weight, current = pq.get()
        visited[current] = True
        
        if dist[current] < min_weight:
            continue
        
        for node, weight in adj_list[current]:
            if visited[node]:
                continue
            new_dist = dist[current] + weight
            dist[node] = new_dist if dist[node] == None else min(dist[node], new_dist)
            if dist[node] == new_dist:
                prev[node] = current
                pq.put((new_dist, node))
    return dist, prev

def get_path(adj_list, start, end):
    
    dist, prev = dijkstra_shortest_path(adj_list, start)
    
    path = []
    
    if prev[end] == None:
        return path
    
    current = prev[end]
    path.append(end)
    while current != None:
        path.append(current)
        current = prev[current]
    return path[::-1]

#testing

adj_list = [
        [(1, 4), (2, 1)],
        [(3, 1)],
        [(1, 2), (3, 5)],
        [(4, 3)],
        []
    ]

print(get_path(adj_list, 1, 4))
        
    
